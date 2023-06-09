package com.web.tp.tp_s6_p14_web_design_mai_2022.utils.DAO;




import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.*;
import org.postgresql.util.PSQLException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectBDD {
    public String getNomTable(){
        String table = (getClass().getAnnotation(TableAnnotation.class).nameTable().equals(""))?getClass().getSimpleName():getClass().getAnnotation(TableAnnotation.class).nameTable();
        return table;
    }
    private Field[] getField()
    {
        return getClass().getDeclaredFields();
    }
    private  <T> T SetResult(ObjectBDD obj,ResultSet res, Connection con) throws Exception{
        Field[] list_field = obj.getField();
        for (Field f:list_field) {
            f.setAccessible(true);
            KeyAnnotation column = f.getAnnotation(KeyAnnotation.class);
            ForeignKeyAnnotation fk = f.getAnnotation(ForeignKeyAnnotation.class);
//                String setterName = "set" +-
            if ((column != null)&&(fk==null)) {
                String nameColumn = (column.column().equals(""))?f.getName(): column.column();
//                Class t = res.getObject(nameColumn).getClass();
//                Method setter = obj.getClass().getDeclaredMethod(setterName,(int)t);
//                setter.invoke(obj,res.getObject(nameColumn));
                f.set(obj,res.getObject(nameColumn));
            }
            if ((column == null)&&(fk!=null)) {
                String name = (fk.name().equals(""))?f.getName():fk.name();
//                Class t = res.getObject(name).getClass();
//                Method setter = obj.getClass().getDeclaredMethod(setterName,t);
                Object id = get((ObjectBDD) obj,name);
                ObjectBDD objectclass = (ObjectBDD) f.getType().newInstance();
                T val = (T) objectclass.findById(con, String.valueOf(id));
//                setter.invoke(obj,val);
                f.set(obj,val);
            }
        }
        return (T) obj;
    }
    private String getPrimaryKey(){
        Field[] list = getField();
        for (Field f:list) {
            IdAnnotation id = f.getAnnotation(IdAnnotation.class);
            if (id != null) {
                return id.name();
            }
        }
        return null;
    }
    static Object get(ObjectBDD objectBdd, String column) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String getterName = "get" + column.substring(0, 1).toUpperCase() + column.substring(1);
        Method getter = objectBdd.getClass().getMethod(getterName);
        Object value = getter.invoke(objectBdd);
        return value;
    }
    public <T>T findById(Connection con,String id) throws Exception {
        String Primary = getPrimaryKey();
        String sql = "select * from "+getNomTable()+" where "+Primary+"='"+id+"'";
        PreparedStatement stat = con.prepareStatement(sql);
        ResultSet res =  stat.executeQuery();
        if (res.next()) SetResult(this,res,con);
        T value = (T) this;
        return value;
    }
    private Field[] getFieldValid(){
        Field[] f = getField();
        ArrayList<Field> list = new ArrayList<>();
        for (Field t:f) {
            KeyAnnotation column = t.getAnnotation(KeyAnnotation.class);
            IdAnnotation id = t.getAnnotation(IdAnnotation.class);
            DefaultValueAnnotation defaults = t.getAnnotation(DefaultValueAnnotation.class);
            if ((defaults != null)||(id!=null)||(column!=null)) {
                list.add(t);
            }
        }
        return list.toArray(new Field[0]);
    }
    public void updateById(Connection con) throws Exception{
        Field[] f = getFieldValid();
        String primary = getPrimaryKey();
        Object id_v = get((ObjectBDD) this,primary);
        String sql = "update "+getNomTable()+" set ";
        String cond = "where "+primary+"='"+ id_v +"'";
        for( int i=0;i<f.length;i++) {
            f[i].setAccessible(true);
            KeyAnnotation column = f[i].getAnnotation(KeyAnnotation.class);
            IdAnnotation id = f[i].getAnnotation(IdAnnotation.class);
            DefaultValueAnnotation defaults = f[i].getAnnotation(DefaultValueAnnotation.class);
            ForeignKeyAnnotation fk = f[i].getAnnotation(ForeignKeyAnnotation.class);
            String condition = "";
            if ((fk == null)&&(column!=null)) {
                String nameColumn = (column.column().equals(""))?f[i].getName(): column.column();
                Object value = get(this,nameColumn);
                if (value instanceof Integer || value instanceof Double || value instanceof Float) {
                    condition += value;
                } else {
                    condition += "'" + value + "'";
                }
                if (i == f.length - 1) {
                    sql += nameColumn + "=" + condition;
                    break;
                }
                sql += nameColumn + "=" + condition + ",";
            }
        }
        sql+=cond;
        System.out.println(sql);
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(sql);
            stat.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally{
            if (stat != null) {
                stat.close();
            }
        }
    }
    public void UpdateByid(Connection con) throws Exception {
        updateById(con);
        if (!con.isClosed()){
            con.close();
        }
    }
    public <T> T saveAll(Connection con) throws Exception {
        T returnee = save(con);
        if (con!=null){
            con.close();
        }
        return returnee;
    }
    public <T> T save(Connection con) throws Exception {
        Field[] f = getFieldValid();
        String sql = "insert into "+getNomTable()+"(";
        String values = " values (";
        for( int i=0;i<f.length;i++) {
            f[i].setAccessible(true);
            KeyAnnotation column = f[i].getAnnotation(KeyAnnotation.class);
            IdAnnotation id = f[i].getAnnotation(IdAnnotation.class);
            DefaultValueAnnotation defaults = f[i].getAnnotation(DefaultValueAnnotation.class);
            ForeignKeyAnnotation fk = f[i].getAnnotation(ForeignKeyAnnotation.class);
            if ((fk == null)&&(column!=null)) {
                String nameColumn = (column.column().equals(""))?f[i].getName(): column.column();
                Object value = get(this,f[i].getName());
                String cond = "";
                if (value instanceof Integer || value instanceof Double || value instanceof Float) {
                    cond+=value;
                }else {
                    cond+="'"+value+"'";
                }
                if ((defaults != null)||(id!=null)) {
                    if (i==f.length-1) {
                        sql+=")";
                        values+=")";
                    }
                    if ((id!=null)&&(!id.sequence().equals(""))){
                        sql+= nameColumn+",";
//                        nextval('client_id_seq'::regclass)
                        values+="nextval('"+id.sequence()+"'::regclass),";
                    }
                    continue;
                }
                if (i==f.length-1) {
                    sql+= nameColumn+")";
                    values+=cond+")";
                    break;
                }
                sql+= nameColumn+",";
                values+=cond+",";
            }
        }
        sql+=values;
//        System.out.println(sql);
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(sql);
            stat.executeUpdate();
            return findlast(con);
//            con.commit();
        } catch (Exception e) {
//            con.rollback();
            if (e instanceof PSQLException){
                e.printStackTrace();
                if (stat!=null){
                    stat.close();
                }
                return null;
            }else {
                System.out.println(e.getClass());
                throw e;
            }
        }
        finally{
            if (stat!=null){
                stat.close();
            }
//            con.close();
        }
    }
    public <T>ArrayList<T> SelectAll(Connection con) throws Exception{
        String sql = "select * from "+getNomTable();
        return SelectAllByQuery(con,sql);
    }
    private String createQuery() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Field[] f = getFieldValid();
        String sql = "select * from "+getNomTable()+" where ";
        for( int i=0;i<f.length;i++) {
            f[i].setAccessible(true);
            KeyAnnotation column = f[i].getAnnotation(KeyAnnotation.class);
            ForeignKeyAnnotation fk = f[i].getAnnotation(ForeignKeyAnnotation.class);
            if (fk == null) {
                String nameColumn = (column.column().equals(""))?f[i].getName(): column.column();
                Object value = get(this,f[i].getName());

                if (value==null){
//                    sql+="1=1 and ";
                    if (i==f.length-1) {
                        sql+="1=1";
                        break;
                    }
                    continue;
                }
                Object test = 0;
                if (value==test){
//                    sql+="1=1 and ";
                    if (i==f.length-1) {
                        sql+="1=1";
                        break;
                    }
                    continue;
                }
                if (value instanceof String) {
                    sql+="upper("+nameColumn+") like upper('%"+value+"%') and ";
                }
                else {
                    sql+=nameColumn+"='"+value+"' and ";
                }
                if (i==f.length-1) {
                    sql+="1=1";
                    break;
                }
            }
        }
        return sql;
    }
    public <T> ArrayList<T> SelectAllByQuery(Connection con,String sql) throws Exception {
        try {
            return findAllByQuery(con,sql);
        }catch (Exception e){
            throw e;
        }finally {
            con.close();
        }
    }
    public <T> ArrayList<T> findAllByQuery(Connection con,String sql) throws Exception {
        PreparedStatement stat = null;
        ArrayList<T> list = new ArrayList<T>();
        try {
            stat = con.prepareStatement(sql);
            ResultSet res =  stat.executeQuery();
            int j=0;
            while (res.next()) {
                T obj = (T) this.getClass().newInstance();
//                T obj = (T) this.getClass().getConstructor().newInstance();
                obj = (T) SetResult((ObjectBDD) obj,res,con);
                list.add(obj);
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            assert stat != null;
            stat.close();
        }
        return list;
    }
    public Object getValueFromFunction(String sql,String key,Connection con) throws SQLException {
        PreparedStatement stat = null;
        Object reste = 0;
        try {
            stat = con.prepareStatement(sql);
            ResultSet res = stat.executeQuery();
            if (res.next()){
                reste = res.getObject(key);
            }
        }catch (Exception e){
            throw e;
        }
        finally {
            stat.close();
            con.close();
        }
        if ((reste instanceof  Long) || (reste instanceof Integer)) reste = Integer.valueOf( String.valueOf(reste));
        return reste;
    }
    public <T> T findlast(Connection con) throws Exception {
        String sql = "select * from "+getNomTable()+" order by "+getPrimaryKey()+" desc limit 1";
        return (T) findAllByQuery(con,sql).get(0);
    }
    public <T> ArrayList<T> paginateSelectAll(int maxValue,int row,Connection con) throws Exception {
        String sql = "select * from "+getNomTable()+" limit "+maxValue+" OFFSET "+row;
        return SelectAllByQuery(con,sql);
    }
    public <T>ArrayList<T> paginateNativeSql(String sql,int maxValue,int row,Connection con) throws Exception {
        sql += " limit "+maxValue+" OFFSET "+row;
        return SelectAllByQuery(con,sql);
    }
    public <T> ArrayList<T> search(Connection con) throws Exception {
        String sql = createQuery();
        System.out.println(sql);
//        return null;
        return SelectAllByQuery(con,sql);
    }
}
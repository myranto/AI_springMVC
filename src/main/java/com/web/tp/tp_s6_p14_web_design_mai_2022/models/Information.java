package com.web.tp.tp_s6_p14_web_design_mai_2022.models;

import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.Connex.Connexion;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.DAO.ObjectBDD;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Information extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation
    private int id;
    @KeyAnnotation
    private String title;
    @KeyAnnotation
    @DefaultValueAnnotation
    private Timestamp date_creation;
    @KeyAnnotation
    private String content;
    @KeyAnnotation
    private String image;
    @KeyAnnotation
    private String state="f";
    @KeyAnnotation
    private int id_autor;
    @ForeignKeyAnnotation(name = "id_autor")
    private Autor autor;

    private String sql = null;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public Information() {
    }

    public Information(String title, Timestamp date_creation, String content, String image, int id_autor) {
        this.title = title;
        this.date_creation = date_creation;
        this.content = content;
        this.image = image;
        this.id_autor = id_autor;
    }
    private String setDateQuery(Timestamp debut,Timestamp fin){
        String sql = " date_creation ";
        if ((debut != null)&&(fin == null)){
            return sql+" >= '"+debut+"'";
        } else if ((debut == null)&&(fin != null)){
            return sql+" <= '"+fin+"'";
        } else if ((debut != null)&&(fin != null)){
            return sql+" between '"+debut+"' and '"+fin+"'";
        }
        return "1=1";
    }
    private String setWordQuery(String title,String content){
            return " upper(title) like upper('%"+title+"%') or content like upper('%"+content+"%') ";
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
    public ArrayList<Information> SelectAllPubliateNp() throws Exception {
        return SelectAllByQuery( Connexion.getConnection(),"select * from "+getNomTable()+" where state='t'");
    }
    public ArrayList<Information> SelectAllPubliate(int Max,int row) throws Exception {
        return paginateNativeSql("select * from "+getNomTable()+" where state='t'",Max,row, Connexion.getConnection());
    }
    public int searchAll(String sql) throws Exception {
        ArrayList<Information> l = SelectAllByQuery(Connexion.getConnection(),sql);
        return l!=null?l.size():0;
    }
    public ArrayList<Information> getOwnPub(String id) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),"select * from "+getNomTable()+" where id_autor="+id);
    }

    //    recherche avec les champs: title,date_creation,content
//    avec pagination de max row et offset
    public ArrayList<Information> search(String search,Timestamp date_creation,Timestamp fin,int Maxrow,int offset,boolean isPubliate) throws Exception {
        if (getSql()!=null){
            return paginateNativeSql(getSql(),Maxrow,offset,Connexion.getConnection());
        }
        String cond = (isPubliate)?"state='t'":"1=1";
        String sql = "select * from information where ("+setDateQuery(date_creation,fin)+" and "+setWordQuery(search,search)+") and "+cond;
        System.out.println(sql);
        setSql(sql);
        return paginateNativeSql(sql,Maxrow,offset,Connexion.getConnection());
    }

}

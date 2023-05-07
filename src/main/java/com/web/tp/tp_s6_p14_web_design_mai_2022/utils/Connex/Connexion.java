package com.web.tp.tp_s6_p14_web_design_mai_2022.utils.Connex;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    public static Connection con = null;



        public static Connection getConnection() throws Exception {
            if (con != null && !con.isClosed()) {
                return con;
            }
        try{
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://arjuna.db.elephantsql.com:5432/uatfarbv";
            String user="uatfarbv";
            String password="vuxrfyaphM6FH8kmOEMZbXlr6sNCJPbC";
            con=(Connection) DriverManager.getConnection(url, user, password);
            return con;
        }
        catch(Exception e){
            throw e;
        }
    }
//    public static Connection getConnection() throws Exception {
//        Connection con=null;
//        try{
//            Class.forName("org.postgresql.Driver");
//            String url="jdbc:postgresql://localhost:5432/info_ai";
//	   		String user="postgres";
//	   		String password="myranto";
//	   		con=(Connection) DriverManager.getConnection(url, user, password);
//            return con;
//        }
//        catch(Exception e){
//            throw e;
//        }
//    }
    public static void main(String[] args) throws Exception {
        System.out.println(Connexion.getConnection());

    }
}

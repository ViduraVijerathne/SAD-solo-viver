/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.SQLException;

/**
 *
 * @author vidur
 */
public class Database {

    private static String URL = "jdbc:mysql://localhost:3306/pos_viver";
    private static String username = "root";
    private static String password = "6jfmd672@V";

    private static java.sql.Connection conn;
//    
//    private Database() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = java.sql.DriverManager.getConnection(URL, username, password);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(URL, username, password);
            System.out.println("Static block");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
//    private static void createConnection(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = java.sql.DriverManager.getConnection(URL, username, password);
//            System.out.println("work here");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public static java.sql.Connection getConnection() {
//        if(conn == null){
//            createConnection();
//        }
        return conn;
    }

}

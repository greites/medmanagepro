package com.medmanagepro;

import dao.DBConnection;
import java.sql.Connection;

public class Medmanagepro {
    public static String URL = "jdbc:mysql://localhost:3306/med_manage_pro";
    public static String USER = "root";
    public static String PASSWORD = "";
    
    public static void main(String[] args) {
        DBConnection db = new DBConnection(URL, USER, PASSWORD);
        Connection conn = db.getConnection();
    }  
}

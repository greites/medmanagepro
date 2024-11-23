package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Connection connection = null;

    public DBConnection(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.print("MySQL JDBC Driver not found!");
        } catch (SQLException e) { 
            System.out.println("Error, can't connect to database!");
        }
        
        return connection;
    }
}

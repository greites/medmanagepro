package com.medmanagepro;

import controller.PatientController;
import dao.DBConnection;
import dao.PatientDAO;
import java.sql.Connection;
import java.sql.SQLException;
import view.PatientView;

public class Medmanagepro {
    public static String URL = "jdbc:mysql://localhost:3306/medicalclinic";
    public static String USER = "root";
    public static String PASSWORD = "";
    
    public static void main(String[] args) throws SQLException {
        DBConnection db = new DBConnection(URL, USER, PASSWORD);
        Connection conn = db.getConnection();
        
        PatientDAO patientDAO = new PatientDAO(conn);
        PatientController patientController = new PatientController(patientDAO);
        
        PatientView patientView = new PatientView(patientController);
        patientView.setVisible(true);
    }  
}

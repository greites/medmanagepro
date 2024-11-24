package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import model.SchedulingModel;

public class SchedulingDAO {
    
    public Connection conn;
    
    public SchedulingDAO(Connection conn) {
        this.conn = conn;
    }
    
        
    public SchedulingModel save(SchedulingModel scheduling) throws SQLException {
        final String INSERT_PATIENT = "INSERT INTO patients (full_name, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement statement = conn.prepareStatement(INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
        
    }
}

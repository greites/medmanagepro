package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.PatientModel;

public class PatientDAO {
    private final Connection conn;

    public PatientDAO(Connection connection) {
        this.conn= connection;
    }
    
    public void save(PatientModel patient) throws SQLException {
        String sql = "INSERT INTO patients (full_name, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, patient.getFullName());
        stmt.setDate(2, Date.valueOf(patient.getBirthDate()));
        stmt.setString(3, patient.getAddress());
        stmt.setString(4, patient.getPhone());
        stmt.setString(5, patient.getEmail());
        
        stmt.executeUpdate();
    }
    
    public Optional<PatientModel> findById(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(convert(resultSet));
            }
        }
        
        return Optional.empty();
    }
    
    public List<PatientModel> findAll() throws SQLException {
        String sql = "SELECT * FROM patients";
        
        List<PatientModel> patients = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            patients.add(convert(resultSet));
        }
 
        return patients;
    }
    
    public PatientModel update(PatientModel patient) throws SQLException {
        String sql = "UPDATE patients SET full_name = ?, birth_date = ?, address = ?, phone = ?, email = ? WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, patient.getFullName());
        statement.setDate(2, Date.valueOf(patient.getBirthDate()));
        statement.setString(3, patient.getAddress());
        statement.setString(4, patient.getPhone());
        statement.setString(5, patient.getEmail());
        statement.setInt(6, patient.getId());

        statement.executeUpdate();
        
        return patient;
    }
        
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
    private PatientModel convert(ResultSet resultSet) throws SQLException {
        PatientModel patient = new PatientModel();
        patient.setId(resultSet.getInt("id"));
        patient.setFullName(resultSet.getString("full_name"));
        patient.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        patient.setAddress(resultSet.getString("address"));
        patient.setPhone(resultSet.getString("phone"));
        patient.setEmail(resultSet.getString("email"));
        return patient;
    }
}

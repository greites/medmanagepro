package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.PatientModel;

public class PatientDAO {
    
    public Connection conn;
    
    public PatientDAO(Connection conn) {
        this.conn = conn;
    }
    
    public PatientModel save(PatientModel patient) throws SQLException {
        final String INSERT_PATIENT = "INSERT INTO patients (full_name, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement statement = conn.prepareStatement(INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, patient.getFullName());
        statement.setDate(2, Date.valueOf(patient.getBirthDate()));
        statement.setString(3, patient.getAddress());
        statement.setString(4, patient.getPhone());
        statement.setString(5, patient.getEmail());

        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setId(generatedKeys.getLong(1));
                }
            }
        }
        
        return patient;
    }
    
    public Optional<PatientModel> findById(Long id) throws SQLException {
        final String SELECT_PATIENT_BY_ID = "SELECT * FROM patients WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(SELECT_PATIENT_BY_ID);
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(mapResultSetToPatient(resultSet));
            }
        }
        
        return Optional.empty();
    }
    
    public List<PatientModel> findAll() throws SQLException {
        final String SELECT_ALL_PATIENTS = "SELECT * FROM patients";
        
        List<PatientModel> patients = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(SELECT_ALL_PATIENTS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            patients.add(mapResultSetToPatient(resultSet));
        }
 
        return patients;
    }
    
    public PatientModel update(PatientModel patient) throws SQLException {
        final String UPDATE_PATIENT = "UPDATE patients SET full_name = ?, birth_date = ?, address = ?, phone = ?, email = ? WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(UPDATE_PATIENT);
        
        statement.setString(1, patient.getFullName());
        statement.setDate(2, Date.valueOf(patient.getBirthDate()));
        statement.setString(3, patient.getAddress());
        statement.setString(4, patient.getPhone());
        statement.setString(5, patient.getEmail());
        statement.setLong(6, patient.getId());

        statement.executeUpdate();
        
        return patient;
    }
    
    public void delete(Long id) throws SQLException {
        final String DELETE_PATIENT = "DELETE FROM patients WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(DELETE_PATIENT);
        statement.setLong(1, id);
        statement.executeUpdate();
    }
    
    private PatientModel mapResultSetToPatient(ResultSet resultSet) throws SQLException {
        PatientModel patient = new PatientModel();
        patient.setId(resultSet.getLong("id"));
        patient.setFullName(resultSet.getString("full_name"));
        patient.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        patient.setAddress(resultSet.getString("address"));
        patient.setPhone(resultSet.getString("phone"));
        patient.setEmail(resultSet.getString("email"));
        return patient;
    }
}

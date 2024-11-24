package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.MedicalRecordModel;
import model.PatientModel;

public class MedicalRecordDAO {
    
    private final Connection conn;
    private final PatientDAO patientDAO;
    
    public MedicalRecordDAO(Connection conn, PatientDAO patientDAO) {
        this.conn = conn;
        this.patientDAO = patientDAO;
    }
    
    public void save(MedicalRecordModel medicalRecord) throws SQLException {
        String sql = "INSERT INTO medical_records (patient_id, consultation_date, symptoms, diagnosis, treatment) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, medicalRecord.getPatient().getId());
        stmt.setDate(2, Date.valueOf(medicalRecord.getDate()));
        stmt.setString(3, medicalRecord.getSymptoms());
        stmt.setString(4, medicalRecord.getDiagnosis());
        stmt.setString(5, medicalRecord.getTreatment());
        
        stmt.executeUpdate();
    }
    
    public Optional<MedicalRecordModel> findById(int id) throws SQLException {
        String sql = "SELECT * FROM medical_records WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(convert(resultSet));
            }
        }
        
        return Optional.empty();
    }
    
    public List<MedicalRecordModel> findAll() throws SQLException {
        String sql = "SELECT * FROM medical_records";
        
        List<MedicalRecordModel> medicalRecords = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            medicalRecords.add(convert(resultSet));
        }
 
        return medicalRecords;
    }
    
    public MedicalRecordModel update(MedicalRecordModel medicalRecord) throws SQLException {
        String sql = "UPDATE medical_records SET patient_id = ?, consultation_date = ?, symptoms = ?, diagnosis = ?, treatment = ? WHERE id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, medicalRecord.getPatient().getId());
        stmt.setDate(2, Date.valueOf(medicalRecord.getDate()));
        stmt.setString(3, medicalRecord.getSymptoms());
        stmt.setString(4, medicalRecord.getDiagnosis());
        stmt.setString(5, medicalRecord.getTreatment());
        stmt.setInt(6, medicalRecord.getId());

        stmt.executeUpdate();
        
        return medicalRecord;
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM medical_records WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
    public MedicalRecordModel convert(ResultSet resultSet) throws SQLException {
        PatientModel patientModel = patientDAO.findById(resultSet.getInt("patient_id")).get();
        
        MedicalRecordModel medicalRecord = new MedicalRecordModel();
        medicalRecord.setId(resultSet.getInt("id"));
        medicalRecord.setPatient(patientModel);
        medicalRecord.setDate(resultSet.getDate("consultation_date").toLocalDate());
        medicalRecord.setSymptoms(resultSet.getString("symptoms"));
        medicalRecord.setDiagnosis(resultSet.getString("diagnosis"));
        medicalRecord.setTreatment(resultSet.getString("treatment"));
        
        return medicalRecord;
    }
}

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.MedicalRecordModel;

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
}

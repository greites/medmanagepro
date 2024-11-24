package controller;

import dao.MedicalRecordDAO;
import dao.PatientDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.MedicalRecordModel;
import model.PatientModel;
import service.Date;

public class MedicalRecordController {
    
    private MedicalRecordDAO medicalRecordDAO;
    private PatientDAO patientDAO;
    
    public MedicalRecordController(MedicalRecordDAO medicalRecordDAO, PatientDAO patientDAO) {
        this.medicalRecordDAO = medicalRecordDAO;
        this.patientDAO = patientDAO;
    }
    
    public void create(String patientID, String data, String symptoms, String diagnosis, String treatment) throws SQLException{
        LocalDate newDate = Date.format(data);
        int newIdPatient = Integer.parseInt(patientID);
       
        PatientModel patient = patientDAO.findById(newIdPatient).get();
        
        MedicalRecordModel medicalRecord = new MedicalRecordModel(0, patient, newDate, symptoms, diagnosis, treatment);
        
        medicalRecordDAO.save(medicalRecord);
    }
    
    public MedicalRecordModel get(String id) throws SQLException {
        int newId = Integer.parseInt(id);
        return medicalRecordDAO.findById(newId).get();
    }
    
    public List<MedicalRecordModel> getAll() throws SQLException {
        return medicalRecordDAO.findAll();
    }
    
    public void update(String id, String patientID, String data, String symptoms, String diagnosis, String treatment) throws SQLException {
        LocalDate newDate = Date.format(data);
        int newId = Integer.parseInt(id);
        int newIdPatient = Integer.parseInt(patientID);
       
        PatientModel patient = patientDAO.findById(newIdPatient).get();
        
        MedicalRecordModel medicalRecord = new MedicalRecordModel(newId, patient, newDate, symptoms, diagnosis, treatment);
        
        medicalRecordDAO.update(medicalRecord);
    }
    
    public void delete(String id) throws SQLException {
        int newId = Integer.parseInt(id);
        patientDAO.delete(newId);
    } 
}


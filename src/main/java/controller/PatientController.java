package controller;

import dao.PatientDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.PatientModel;
import service.Date;
import service.Phone;
import service.Email;

public class PatientController {
    
    private PatientDAO patientDAO;
    
    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }
    
    public void create(String name,String nasc,String address,String phone,String email) throws SQLException {
        LocalDate newDate = Date.format(nasc);
        
        Phone.validate(phone);
        Email.validate(email);
        
        PatientModel patient = new PatientModel(0, name, newDate, address, phone, email);
        patientDAO.save(patient);
    }
    
    public PatientModel get(String id) throws SQLException {
        int newId = Integer.parseInt(id);
        return patientDAO.findById(newId).get();
    }
    
    public List<PatientModel> getAll() throws SQLException {
        return patientDAO.findAll();
    }
    
    public void update(String id, String name,String nasc,String address,String phone,String email) throws SQLException {
        LocalDate newDate = Date.format(nasc);
        int newId = Integer.parseInt(id);
        
        Phone.validate(phone);
        Email.validate(email);
        
        PatientModel patient = new PatientModel(newId, name, newDate, address, phone, email);
        patientDAO.update(patient);
    }
    
    public void delete(String id) throws SQLException {
        int newId = Integer.parseInt(id);
        patientDAO.delete(newId);
    }
}

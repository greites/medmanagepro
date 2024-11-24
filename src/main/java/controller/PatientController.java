package controller;

import dao.PatientDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import model.PatientModel;
import service.Date;
import service.Email;
import service.Phone;

public class PatientController {
    
    private PatientDAO patientDAO;
    
    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }
    
    public PatientModel create(String name, String nasc, String address, String phone, String email) throws Exception {
        LocalDate newDate = Date.format(nasc);
        
        Phone.validate(phone);
        Email.validate(email);     
        
        PatientModel patient = new PatientModel(null, name, newDate, address, phone, email);
        return patientDAO.save(patient);
    }
    
    public Optional<PatientModel> get(String id) throws Exception {
        Long newId = Long.valueOf(id);
        return patientDAO.findById(newId);
    }
    
    public List<PatientModel> getAll() throws Exception {
        return patientDAO.findAll();
    }
    
    public PatientModel update(String id, String name, String nasc, String address, String phone, String email) throws Exception {
        Long newId = Long.valueOf(id);
        LocalDate newDate = Date.format(nasc);
        
        Phone.validate(phone);
        Email.validate(email);
        
        PatientModel patient = new PatientModel(newId, name, newDate, address, phone, email);
        return patientDAO.update(patient);
    }
    
    public void delete(String id) throws Exception {
        Long newId = Long.valueOf(id);
        patientDAO.delete(newId);
    }
}

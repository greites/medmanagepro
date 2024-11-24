package controller;

import dao.AppointmentDAO;
import dao.PatientDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import model.AppointmentModel;
import model.PatientModel;
import service.Date;
import service.Time;

public class AppointmentController {
    
    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    
    public AppointmentController(AppointmentDAO appointmentDAO, PatientDAO patientDAO) {
        this.appointmentDAO = appointmentDAO;
        this.patientDAO = patientDAO;
    }
    
    public void create(String patientID, String data, String time, String doctor, String notes) throws SQLException{
        LocalDate newDate = Date.format(data);
        LocalTime newTime = Time.format(time);
        
        int newIdPatient = Integer.parseInt(patientID);
       
        PatientModel patient = patientDAO.findById(newIdPatient).get();
        
        AppointmentModel appointment = new AppointmentModel(0, patient, newDate, newTime, doctor, notes);
        
        appointmentDAO.save(appointment);
    }
    
    public AppointmentModel get(String id) throws SQLException {
        int newId = Integer.parseInt(id);
        return appointmentDAO.findById(newId).get();
    }
    
    public List<AppointmentModel> getAll() throws SQLException {
        return appointmentDAO.findAll();
    }
    
    public void update(String id, String patientID, String data, String time, String doctor, String notes) throws SQLException {
        LocalDate newDate = Date.format(data);
        LocalTime newTime = Time.format(time);
        
        int newId = Integer.parseInt(id);
        int newIdPatient = Integer.parseInt(patientID);
        
        PatientModel patient = patientDAO.findById(newIdPatient).get();
        
        AppointmentModel appointment = new AppointmentModel(newId, patient, newDate, newTime, doctor, notes);
        
        appointmentDAO.update(appointment);
    }
    
    public void delete(String id) throws SQLException {
        int newId = Integer.parseInt(id);
        patientDAO.delete(newId);
    }
}

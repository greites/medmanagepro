package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.AppointmentModel;
import model.PatientModel;

public class AppointmentDAO {
    
    private final Connection conn;
    private final PatientDAO patientDAO;
    
    public AppointmentDAO(Connection conn, PatientDAO patientDAO) {
        this.conn = conn;
        this.patientDAO = patientDAO;
    }
    
    public void save(AppointmentModel appointment) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, appointment_date, appointment_time, doctor, notes) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, appointment.getPatient().getId());
        stmt.setDate(2, Date.valueOf(appointment.getDate()));
        stmt.setTime(3, Time.valueOf(appointment.getTime()));
        stmt.setString(4, appointment.getDoctor());
        stmt.setString(5, appointment.getNotes());
        
        stmt.executeUpdate();
    }
    
    public Optional<AppointmentModel> findById(int id) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(convert(resultSet));
            }
        }
        
        return Optional.empty();
    }
    
    public List<AppointmentModel> findAll() throws SQLException {
        String sql = "SELECT * FROM appointments";
        
        List<AppointmentModel> appointments = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            appointments.add(convert(resultSet));
        }
 
        return appointments;
    }
    
    public AppointmentModel update(AppointmentModel appointment) throws SQLException {
        String sql = "UPDATE appointments SET patient_id = ?, appointment_date = ?, appointment_time = ?, doctor = ?, notes = ? WHERE id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, appointment.getPatient().getId());
        stmt.setDate(2, Date.valueOf(appointment.getDate()));
        stmt.setTime(3, Time.valueOf(appointment.getTime()));
        stmt.setString(4, appointment.getDoctor());
        stmt.setString(5, appointment.getNotes());
        stmt.setInt(6, appointment.getId());

        stmt.executeUpdate();
        
        return appointment;
    }
    
            
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM appointments WHERE id = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
    
    public AppointmentModel convert(ResultSet resultSet) throws SQLException {
        PatientModel patientModel = patientDAO.findById(resultSet.getInt("patient_id")).get();
        
        AppointmentModel appointment = new AppointmentModel();
        appointment.setId(resultSet.getInt("id"));
        appointment.setPatient(patientModel);
        appointment.setDate(resultSet.getDate("appointment_date").toLocalDate());
        appointment.setTime(resultSet.getTime("appointment_time").toLocalTime());
        appointment.setDoctor(resultSet.getString("doctor"));
        appointment.setNotes(resultSet.getString("notes"));
        
        return appointment;
    }
}


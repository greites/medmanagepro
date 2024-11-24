package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentModel {
    private int id;
    private PatientModel patient;
    private LocalDate date;
    private LocalTime time;
    private String doctor;
    private String notes;

    // Constructors
    public AppointmentModel() {}

    public AppointmentModel(int id, PatientModel patient, LocalDate appointmentDate, LocalTime appointmentTime, String doctor, String notes) {
        this.id = id;
        this.patient = patient;
        this.date = appointmentDate;
        this.time = appointmentTime;
        this.doctor = doctor;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}


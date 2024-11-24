package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SchedulingModel {
    private Long id;
    private PatientModel patient;
    private LocalDate appointmentDate;
    private LocalTime time;
    private String doctor;
    private String notes;

    public SchedulingModel() {}

    public SchedulingModel(Long id, PatientModel patient, LocalDate appointmentDate, LocalTime time, String doctor, String notes) {
        this.id = id;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.time = time;
        this.doctor = doctor;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
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


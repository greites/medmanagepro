package model;

import java.time.LocalDate;

public class MedicalRecordModel {
    private int id;
    private PatientModel patient;
    private LocalDate date;
    private String symptoms;
    private String diagnosis;
    private String treatment;

    // Constructors
    public MedicalRecordModel() {}

    public MedicalRecordModel(int id, PatientModel patient, LocalDate date, String symptoms, String diagnosis, String treatment) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}


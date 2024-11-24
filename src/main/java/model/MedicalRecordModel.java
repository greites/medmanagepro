package model;

import java.time.LocalDate;

public class MedicalRecordModel {
    private int id;
    private PatientModel patient;
    private LocalDate consultationDate;
    private String symptoms;
    private String diagnosis;
    private String treatment;

    // Constructors
    public MedicalRecordModel() {}

    public MedicalRecordModel(int id, PatientModel patient, LocalDate consultationDate, String symptoms, String diagnosis, String treatment) {
        this.id = id;
        this.patient = patient;
        this.consultationDate = consultationDate;
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

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
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


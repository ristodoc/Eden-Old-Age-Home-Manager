package eden.oldagehome.entity;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class DoctorVisit {
    private LocalDate date;
    private String notes;
    private Doctor doctor;
    private Resident resident;
    private List<String> prescriptions;
    
    public DoctorVisit(LocalDate date, String notes, Doctor doctor, Resident resident) {
        this.date = date;
        this.notes = notes;
        this.doctor = doctor;
        this.resident = resident;
        this.prescriptions = new ArrayList<>();
    }
    
    public String getSummary() {
        return "Visit on " + date + " by Dr. " + doctor.getName() + 
               ". Notes: " + notes + ". Prescriptions: " + String.join(", ", prescriptions);
    }
    
    public void addPrescription(String prescription) {
        prescriptions.add(prescription);
    }
    
    // Getters and setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Resident getResident() { return resident; }
    public void setResident(Resident resident) { this.resident = resident; }
    public List<String> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<String> prescriptions) { this.prescriptions = prescriptions; }
}
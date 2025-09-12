package eden.oldagehome.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a doctor's visit for a resident.
 */
public class DoctorVisit {

    private LocalDate date;
    private String notes;
    private Doctor doctor;
    private List<String> prescriptions;

    public DoctorVisit(LocalDate date, String notes, Doctor doctor) {
        this.date = date;
        this.notes = notes;
        this.doctor = doctor;
        this.prescriptions = new ArrayList<>();
    }

    /**
     * Returns a summary string of the doctor's visit.
     * @return A formatted string with visit details.
     */
    public String getSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String summary = "Visit on: " + date.format(formatter) + "\n" +
                         "Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")\n" +
                         "Notes: " + notes + "\n" +
                         "Prescriptions: " + (prescriptions.isEmpty() ? "None" : String.join(", ", prescriptions));
        return summary;
    }

    // Getters and Setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<String> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<String> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addPrescription(String prescription) {
        this.prescriptions.add(prescription);
    }
}

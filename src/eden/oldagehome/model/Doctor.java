package eden.oldagehome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Doctor.
 */
public class Doctor {

    private String doctorId;
    private String name;
    private String specialization;
    private List<DoctorVisit> visits;

    public Doctor(String doctorId, String name, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.visits = new ArrayList<>();
    }

    /**
     * Adds a visit to the doctor's record.
     * @param v The DoctorVisit to add.
     */
    public void addVisit(DoctorVisit v) {
        this.visits.add(v);
    }

    /**
     * Placeholder method to view a resident's history.
     * @param residentId The ID of the resident.
     */
    public void viewResidentHistory(String residentId) {
        System.out.println("Fetching history for resident " + residentId + "... (not implemented)");
        // In a real application, this would query the database for all visits
        // and other relevant medical info for the given resident.
    }

    // Getters and Setters

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<DoctorVisit> getVisits() {
        return visits;
    }

    public void setVisits(List<DoctorVisit> visits) {
        this.visits = visits;
    }
}

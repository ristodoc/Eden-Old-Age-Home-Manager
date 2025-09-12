package eden.oldagehome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ward within the old age home.
 */
public class Ward {

    private String wardId;
    private String name;
    private List<Resident> patients;
    private List<WardEmployee> employees;

    public Ward(String wardId, String name) {
        this.wardId = wardId;
        this.name = name;
        this.patients = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    /**
     * Adds a patient to the ward.
     * @param p The resident to add.
     */
    public void addPatient(Resident p) {
        this.patients.add(p);
    }

    /**
     * Removes a patient from the ward by their ID.
     * @param patientId The ID of the resident to remove.
     */
    public void removePatient(String patientId) {
        this.patients.removeIf(patient -> patient.getResidentId().equals(patientId));
    }

    /**
     * Generates a summary of all medications required by patients in the ward.
     */
    public void getMedicationSummary() {
        System.out.println("Medication summary for Ward: " + this.name);
        for (Resident resident : patients) {
            System.out.println("  Resident: " + resident.getName());
            for (Medication med : resident.getMedicationList()) {
                System.out.println("    - " + med.getName() + " (Refill needed: " + med.isRefillNeeded() + ")");
            }
        }
    }

    /**
     * Calculates the estimated monthly expenses for the ward.
     * This is a placeholder for a more complex calculation.
     * @return A calculated expense value.
     */
    public double calculateMonthlyExpenses() {
        // Placeholder logic
        double expenses = patients.size() * 500.0; // Example: 500 per patient
        System.out.println("Estimated monthly expenses for Ward " + this.name + ": " + expenses);
        return expenses;
    }

    // Getters and Setters

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Resident> getPatients() {
        return patients;
    }

    public void setPatients(List<Resident> patients) {
        this.patients = patients;
    }

    public List<WardEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<WardEmployee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "wardId='" + wardId + '\'' +
                ", name='" + name + '\'' +
                ", patientCount=" + patients.size() +
                '}';
    }
}

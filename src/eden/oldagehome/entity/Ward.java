package eden.oldagehome.entity;

public class Ward {
    private String wardId;
    private String name;
    private List<Resident> patients;
    private List<WardEmployee> employees;

    public boolean addPatient(Resident p) {
        // Implementation to add patient
        return patients.add(p);
    }

    public boolean removePatient(String patientId) {
        // Implementation to remove patient
        return patients.removeIf(p -> p.getResidentId().equals(patientId));
    }

    public String getMedicationSummary() {
        // Implementation to get medication summary
        return "Medication summary";
    }

    public double calculateMonthlyExpenses() {
        // Implementation to calculate expenses
        return 0.0;
    }

    // Getters and setters
    public String getWardId() { return wardId; }
    public void setWardId(String wardId) { this.wardId = wardId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Resident> getPatients() { return patients; }
    public void setPatients(List<Resident> patients) { this.patients = patients; }
    public List<WardEmployee> getEmployees() { return employees; }
    public void setEmployees(List<WardEmployee> employees) { this.employees = employees; }
}
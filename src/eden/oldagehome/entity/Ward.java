package eden.oldagehome.entity;


import java.util.ArrayList;
import java.util.List;

class Ward {
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
    
    public boolean addPatient(Resident p) {
        return patients.add(p);
    }
    
    public boolean removePatient(String patientId) {
        return patients.removeIf(p -> p.getResidentId().equals(patientId));
    }
    
    public String getMedicationSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Medication Summary for Ward: ").append(this.name).append("\n");
        summary.append("============================================\n");
        
        for (Resident resident : patients) {
            summary.append("Resident: ").append(resident.getName()).append("\n");
            
            if (resident.getMedications().isEmpty()) {
                summary.append("  No medications prescribed\n");
            } else {
                for (Medication medication : resident.getMedications()) {
                    summary.append("  - ").append(medication.getName())
                           .append(" (").append(medication.getDosage()).append(")")
                           .append(" - Qty: ").append(medication.getQuantityLeft())
                           .append("/").append(medication.getMonthlyRequirement())
                           .append(medication.isRefillNeeded() ? " [NEEDS REFILL]" : "")
                           .append("\n");
                }
            }
            summary.append("--------------------------------------------\n");
        }
        
        return summary.toString();
    }
    
    public double calculateMonthlyExpenses() {
        double expenses = 0.0;
        for (Resident resident : patients) {
            for (Medication medication : resident.getMedications()) {
                expenses += medication.getMonthlyRequirement() * 10; // $10 per unit
            }
        }
        return expenses;
    }
    
    public void addEmployee(WardEmployee employee) {
        employees.add(employee);
        employee.setAssignedWard(this);
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

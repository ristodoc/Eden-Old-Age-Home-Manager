package eden.oldagehome.entity;

import java.util.List;

public class Resident {
    private String residentId;
    private String name;
    private int age;
    private String disease;
    private String diet;
    private List<Medication> medications;
    private List<DoctorVisit> doctorVisits;
    
    public boolean addMedication(Medication m) {
        // Implementation to add medication
        return medications.add(m);
    }
    
    public boolean removeMedication(String name) {
        // Implementation to remove medication
        return medications.removeIf(m -> m.getName().equals(name));
    }
    
    public boolean needsRefill() {
        // Implementation to check if refill is needed
        return medications.stream().anyMatch(Medication::isRefillNeeded);
    }
    
    public List<Medication> getMedicationList() {
        return medications;
    }
    
    // Getters and setters
    public String getResidentId() { return residentId; }
    public void setResidentId(String residentId) { this.residentId = residentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }
    public String getDiet() { return diet; }
    public void setDiet(String diet) { this.diet = diet; }
    public List<Medication> getMedications() { return medications; }
    public void setMedications(List<Medication> medications) { this.medications = medications; }
    public List<DoctorVisit> getDoctorVisits() { return doctorVisits; }
    public void setDoctorVisits(List<DoctorVisit> doctorVisits) { this.doctorVisits = doctorVisits; }
}
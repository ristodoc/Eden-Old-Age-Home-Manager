package eden.oldagehome.entity;

import java.util.List;

public class Doctor {
    private String doctorId;
    private String name;
    private String specialization;
    private List<DoctorVisit> visits;

    public boolean addVisit(DoctorVisit v) {
        // Implementation to add visit
        return visits.add(v);
    }

    public List<DoctorVisit> viewResidentHistory(String residentId) {
        // Implementation to get resident history
        return visits.stream()
                .filter(v -> v.getResident().getResidentId().equals(residentId))
                .toList();
    }

    // Getters and setters
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public List<DoctorVisit> getVisits() { return visits; }
    public void setVisits(List<DoctorVisit> visits) { this.visits = visits; }
}
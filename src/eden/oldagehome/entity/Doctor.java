package eden.oldagehome.entity;

import java.util.List;
import java.util.ArrayList;


class Doctor {
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
    
    public boolean addVisit(DoctorVisit v) {
        return visits.add(v);
    }
    
    public List<DoctorVisit> viewResidentHistory(String residentId) {
        List<DoctorVisit> residentVisits = new ArrayList<>();
        for (DoctorVisit visit : visits) {
            if (visit.getResident().getResidentId().equals(residentId)) {
                residentVisits.add(visit);
            }
        }
        return residentVisits;
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

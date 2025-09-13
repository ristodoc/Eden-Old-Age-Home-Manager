package eden.oldagehome.entity;

import java.util.ArrayList;
import java.util.List;
// WardEmployee class
class WardEmployee extends User {
    private String employeeId;
    private Ward assignedWard;
    
    public WardEmployee(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.employeeId = userId;
    }
    
    public List<Resident> viewAssignedPatients() {
        if (assignedWard != null) {
            return assignedWard.getPatients();
        }
        return new ArrayList<>();
    }
    
    public boolean updatePatientInfo(Resident p) {
        System.out.println("Patient information updated: " + p.getName());
        return true;
    }
    
    public List<Medication> generateMonthlyRefillList() {
        List<Medication> refillList = new ArrayList<>();
        if (assignedWard != null) {
            for (Resident resident : assignedWard.getPatients()) {
                for (Medication medication : resident.getMedications()) {
                    if (medication.isRefillNeeded()) {
                        refillList.add(medication);
                    }
                }
            }
        }
        return refillList;
    }
    
    public boolean logDoctorVisit(Resident p, DoctorVisit v) {
        p.getDoctorVisits().add(v);
        System.out.println("Doctor visit logged for resident: " + p.getName());
        System.out.println("Visit details: " + v.getSummary());
        return true;
    }
    
    public List<LoginEntry> viewLoginHistory() {
        return LoginLogger.getInstance().getUserLogHistory(this.userId);
    }
    
    // Getters and setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public Ward getAssignedWard() { return assignedWard; }
    public void setAssignedWard(Ward assignedWard) { this.assignedWard = assignedWard; }
    
    @Override
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Employee login successful! Welcome " + this.name);
            LoginLogger.getInstance().recordLogin(this.userId);
            return true;
        }
        return false;
    }
    
    @Override
    public void logout() {
        LoginLogger.getInstance().recordLogout(this.userId);
        System.out.println("Employee logged out successfully");
    }
    
    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully");
            return true;
        }
        System.out.println("Password change failed: Incorrect old password");
        return false;
    }
}

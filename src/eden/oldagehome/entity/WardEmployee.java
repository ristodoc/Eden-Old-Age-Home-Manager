package eden.oldagehome.entity;

import java.util.List;

public class WardEmployee extends User {
    private String employeeId;
    private Ward assignedWard;

    public List<Resident> viewAssignedPatients() {
        // Implementation to fetch assigned patients
        return null;
    }

    public boolean updatePatientInfo(Resident p) {
        // Implementation to update patient info
        return false;
    }

    public List<Medication> generateMonthlyRefillList() {
        // Implementation to generate refill list
        return null;
    }

    public boolean logDoctorVisit(Resident p, DoctorVisit v) {
        // Implementation to log doctor visit
        return false;
    }

    public List<LoginEntry> viewLoginHistory() {
        // Implementation to fetch login history
        return null;
    }

    // Getters and setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public Ward getAssignedWard() { return assignedWard; }
    public void setAssignedWard(Ward assignedWard) { this.assignedWard = assignedWard; }

    @Override
    public boolean login(String email, String password) {
        // Implementation to verify employee login
        return false;
    }

    @Override
    public void logout() {
        // Implementation for logout
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        // Implementation to change password
        return false;
    }
}

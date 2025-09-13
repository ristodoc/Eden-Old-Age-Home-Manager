package eden.oldagehome.model;

import java.time.LocalDate;

/**
 * Represents a staff member in the old age home.
 */
public class Staff {
    
    private String staffId;
    private String name;
    private String email;
    private String phoneNumber;
    private String role; // Admin, Doctor, Nurse, Caregiver, Maintenance, etc.
    private String department;
    private LocalDate hireDate;
    private String status; // Active, On Leave, Inactive
    private String shift; // Day, Night, Rotating
    private String wardId; // Assigned ward (if applicable)
    
    public Staff(String staffId, String name, String email, String phoneNumber, String role, 
                String department, LocalDate hireDate, String status, String shift) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.department = department;
        this.hireDate = hireDate;
        this.status = status;
        this.shift = shift;
    }
    
    /**
     * Calculates years of service
     * @return years of service
     */
    public int getYearsOfService() {
        return LocalDate.now().getYear() - hireDate.getYear();
    }
    
    /**
     * Checks if staff member is active
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
    
    /**
     * Checks if staff member is on duty today
     * @return true if on duty, false otherwise
     */
    public boolean isOnDutyToday() {
        // Simple logic - in a real system, this would check against a schedule
        return isActive() && ("Day".equals(shift) || "Rotating".equals(shift));
    }
    
    /**
     * Gets formatted hire date
     * @return formatted hire date string
     */
    public String getFormattedHireDate() {
        return hireDate.toString();
    }
    
    // Getters and Setters
    
    public String getStaffId() {
        return staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public LocalDate getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getShift() {
        return shift;
    }
    
    public void setShift(String shift) {
        this.shift = shift;
    }
    
    public String getWardId() {
        return wardId;
    }
    
    public void setWardId(String wardId) {
        this.wardId = wardId;
    }
    
    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

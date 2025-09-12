package eden.oldagehome.model;

/**
 * Represents a Ward Employee, inheriting from the User class.
 */
public class WardEmployee extends User {

    private Ward assignedWard;

    public WardEmployee(String userId, String name, String email, String password, Ward assignedWard) {
        super(userId, name, email, password);
        this.assignedWard = assignedWard;
    }

    // WardEmployee-specific methods

    public void viewAssignedPatients() {
        if (assignedWard != null) {
            System.out.println("Patients in Ward " + assignedWard.getName() + ":");
            for (Resident resident : assignedWard.getPatients()) {
                System.out.println("- " + resident.getName() + " (ID: " + resident.getResidentId() + ")");
            }
        } else {
            System.out.println("No ward assigned to this employee.");
        }
    }

    public void updatePatientInfo(Resident p) {
        System.out.println("Updating information for patient: " + p.getName());
        // In a real application, this would update the patient's record in the database
    }

    public void generateMonthlyRefillList() {
        if (assignedWard != null) {
            System.out.println("Generating monthly medication refill list for Ward: " + assignedWard.getName());
            for (Resident resident : assignedWard.getPatients()) {
                for (Medication med : resident.getMedicationList()) {
                    if (med.isRefillNeeded()) {
                        System.out.println("  - Resident: " + resident.getName() + ", Medication: " + med.getName());
                    }
                }
            }
        } else {
            System.out.println("No ward assigned.");
        }
    }

    public void logDoctorVisit(Resident p, DoctorVisit v) {
        System.out.println("Logging doctor visit for patient: " + p.getName());
        p.getDoctorVisits().add(v);
        // This would also be persisted to the database
    }

    public void viewLoginHistory() {
        System.out.println("Fetching login history for user: " + this.name);
        // This would query the login_history table in the database
    }

    // Implementation of abstract methods from User class

    @Override
    public void login() {
        System.out.println("Ward Employee " + this.name + " logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Ward Employee " + this.name + " logged out.");
    }

    @Override
    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
        System.out.println("Ward Employee " + this.name + " changed password.");
    }

    // Getters and Setters

    public Ward getAssignedWard() {
        return assignedWard;
    }

    public void setAssignedWard(Ward assignedWard) {
        this.assignedWard = assignedWard;
    }
}

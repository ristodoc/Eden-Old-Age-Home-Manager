package eden.oldagehome.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class OldAgeHomeManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Old Age Home Management System ===\n");
        
        // Create admin
        Admin admin = new Admin("ADMIN001", "System Administrator", "admin@example.com", "admin123");
        
        // Create wards
        Ward generalWard = new Ward("WARD001", "General Care");
        Ward specialWard = new Ward("WARD002", "Special Care");
        
        // Add wards to admin
        admin.addWard(generalWard);
        admin.addWard(specialWard);
        
        // Create employees
        WardEmployee emp1 = new WardEmployee("EMP001", "John Doe", "emp1@example.com", "emp123");
        WardEmployee emp2 = new WardEmployee("EMP002", "Jane Smith", "emp2@example.com", "emp456");
        
        // Assign employees to wards
        generalWard.addEmployee(emp1);
        specialWard.addEmployee(emp2);
        
        // Create residents
        Resident resident1 = new Resident("RES001", "Robert Brown", 75, "Arthritis", "Low Sodium");
        Resident resident2 = new Resident("RES002", "Mary Johnson", 82, "Diabetes", "Diabetic");
        Resident resident3 = new Resident("RES003", "William Davis", 78, "Hypertension", "Low Fat");
        
        // Add residents to wards
        generalWard.addPatient(resident1);
        generalWard.addPatient(resident2);
        specialWard.addPatient(resident3);
        
        // Create medications
        Medication med1 = new Medication("Metformin", "500mg twice daily", 15, 60);
        Medication med2 = new Medication("Lisinopril", "10mg daily", 5, 30);
        Medication med3 = new Medication("Insulin", "20 units daily", 25, 30);
        Medication med4 = new Medication("Amlodipine", "5mg daily", 2, 30);
        
        // Assign medications to residents
        resident1.addMedication(med1);
        resident1.addMedication(med2);
        resident2.addMedication(med3);
        resident3.addMedication(med4);
        
        // Create doctors
        Doctor doctor1 = new Doctor("DOC001", "Dr. Sarah Wilson", "Geriatrics");
        Doctor doctor2 = new Doctor("DOC002", "Dr. Michael Chen", "Endocrinology");
        
        // Create doctor visits
        DoctorVisit visit1 = new DoctorVisit(LocalDate.of(2023, 10, 15), 
                                           "Routine checkup, patient is stable", 
                                           doctor1, resident1);
        visit1.addPrescription("Continue current medications");
        
        DoctorVisit visit2 = new DoctorVisit(LocalDate.of(2023, 10, 16), 
                                           "Adjusted insulin dosage", 
                                           doctor2, resident2);
        visit2.addPrescription("Increase insulin to 25 units daily");
        
        // Log doctor visits
        emp1.logDoctorVisit(resident1, visit1);
        emp2.logDoctorVisit(resident2, visit2);
        
        // Demo: Admin operations
        System.out.println("\n1. Admin Operations:");
        admin.login("admin@example.com", "admin123");
        
        System.out.println("\n2. View All Wards:");
        for (Ward ward : admin.viewAllWards()) {
            System.out.println("Ward: " + ward.getName() + " (ID: " + ward.getWardId() + ")");
            System.out.println("  Patients: " + ward.getPatients().size());
            System.out.println("  Employees: " + ward.getEmployees().size());
        }
        
        System.out.println("\n3. Generate Report:");
        System.out.println(admin.generateReport());
        
        System.out.println("\n4. Manage Budget:");
        admin.manageBudget(5000.0);
        
        admin.logout();
        
        // Demo: Employee operations
        System.out.println("\n5. Employee Operations:");
        emp1.login("emp1@example.com", "emp123");
        
        System.out.println("\n6. View Assigned Patients:");
        for (Resident patient : emp1.viewAssignedPatients()) {
            System.out.println("Patient: " + patient.getName() + " (Age: " + patient.getAge() + ")");
        }
        
        System.out.println("\n7. Generate Medication Refill List:");
        for (Medication med : emp1.generateMonthlyRefillList()) {
            System.out.println("Medication: " + med.getName() + 
                             " - Qty: " + med.getQuantityLeft() + 
                             "/" + med.getMonthlyRequirement());
        }
        
        System.out.println("\n8. View Medication Summary for Ward:");
        System.out.println(generalWard.getMedicationSummary());
        
        emp1.logout();
        
        System.out.println("\n=== Demo Completed ===");
    }
}
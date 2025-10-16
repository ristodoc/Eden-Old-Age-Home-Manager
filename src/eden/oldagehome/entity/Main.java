


package eden.oldagehome.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Old Age Home Management System ===\n");
        
        // Initialize the system with test data
        TestDataInitializer initializer = new TestDataInitializer();
        initializer.initializeTestData();
        
        // Run all test cases
        runAdminOperationsTest(initializer);
        runEmployeeOperationsTest(initializer);
        runResidentManagementTest(initializer);
        runMedicationManagementTest(initializer);
        runDoctorVisitTest(initializer);
        runLoginHistoryTest(initializer);
        
        System.out.println("\n=== All Test Cases Completed ===");
    }
    
    // Test Case 1: Admin Operations
    public static void runAdminOperationsTest(TestDataInitializer initializer) {
        System.out.println("\n=== TEST CASE 1: ADMIN OPERATIONS ===");
        
        Admin admin = initializer.getAdmin();
        
        // Admin login
        System.out.println("\n1. Admin Login:");
        admin.login("admin@example.com", "admin123");
        
        // View all wards
        System.out.println("\n2. View All Wards:");
        for (Ward ward : admin.viewAllWards()) {
            System.out.println("Ward: " + ward.getName() + " (ID: " + ward.getWardId() + ")");
            System.out.println("  Patients: " + ward.getPatients().size());
            System.out.println("  Employees: " + ward.getEmployees().size());
        }
        
        // Generate report
        System.out.println("\n3. Generate Report:");
        System.out.println(admin.generateReport());
        
        // Manage budget
        System.out.println("\n4. Manage Budget:");
        admin.manageBudget(5000.0);
        System.out.println("Current budget: $" + admin.getTotalBudget());
        
        // Admin logout
        admin.logout();
    }
    
    // Test Case 2: Employee Operations
    public static void runEmployeeOperationsTest(TestDataInitializer initializer) {
        System.out.println("\n=== TEST CASE 2: EMPLOYEE OPERATIONS ===");
        
        WardEmployee emp1 = initializer.getEmp1();
        
        // Employee login
        System.out.println("\n1. Employee Login:");
        emp1.login("emp1@example.com", "emp123");
        
        // View assigned patients
        System.out.println("\n2. View Assigned Patients:");
        for (Resident patient : emp1.viewAssignedPatients()) {
            System.out.println("Patient: " + patient.getName() + 
                             " (Age: " + patient.getAge() + 
                             ", Disease: " + patient.getDisease() + ")");
        }
        
        // Generate medication refill list
        System.out.println("\n3. Generate Medication Refill List:");
        List<Medication> refillList = emp1.generateMonthlyRefillList();
        if (refillList.isEmpty()) {
            System.out.println("No medications need refill at this time.");
        } else {
            for (Medication med : refillList) {
                System.out.println("Medication: " + med.getName() + 
                                 " - Qty: " + med.getQuantityLeft() + 
                                 "/" + med.getMonthlyRequirement());
            }
        }
        
        // View medication summary for ward
        System.out.println("\n4. View Medication Summary for Ward:");
        Ward generalWard = initializer.getGeneralWard();
        System.out.println(generalWard.getMedicationSummary());
        
        // Employee logout
        emp1.logout();
    }
    
    // Test Case 3: Resident Management
    public static void runResidentManagementTest(TestDataInitializer initializer) {
        System.out.println("\n=== TEST CASE 3: RESIDENT MANAGEMENT ===");
        
        Ward generalWard = initializer.getGeneralWard();
        Ward specialWard = initializer.getSpecialWard();
        
        // Display all residents
        System.out.println("\n1. All Residents in General Ward:");
        for (Resident resident : generalWard.getPatients()) {
            System.out.println("Resident: " + resident.getName() + 
                             " (Age: " + resident.getAge() + 
                             ", Disease: " + resident.getDisease() + ")");
        }
        
        System.out.println("\n2. All Residents in Special Ward:");
        for (Resident resident : specialWard.getPatients()) {
            System.out.println("Resident: " + resident.getName() + 
                             " (Age: " + resident.getAge() + 
                             ", Disease: " + resident.getDisease() + ")");
        }
        
        // Add a new resident
        System.out.println("\n3. Adding a New Resident:");
        Resident newResident = new Resident("RES004", "Alice Johnson", 85, "Dementia", "Soft Foods");
        generalWard.addPatient(newResident);
        System.out.println("Added new resident: " + newResident.getName());
        
        // Remove a resident
        System.out.println("\n4. Removing a Resident:");
        boolean removed = generalWard.removePatient("RES002");
        System.out.println("Resident RES002 removed: " + removed);
        
        // Display updated resident list
        System.out.println("\n5. Updated Residents in General Ward:");
        for (Resident resident : generalWard.getPatients()) {
            System.out.println("Resident: " + resident.getName() + 
                             " (Age: " + resident.getAge() + 
                             ", Disease: " + resident.getDisease() + ")");
        }
    }
    
    // Test Case 4: Medication Management
    public static void runMedicationManagementTest(TestDataInitializer initializer) {
        System.out.println("\n=== TEST CASE 4: MEDICATION MANAGEMENT ===");
        
        Resident resident1 = initializer.getResident1();
        Resident resident3 = initializer.getResident3();
        
        // Display medications for a resident
        System.out.println("\n1. Medications for Resident 1:");
        for (Medication med : resident1.getMedications()) {
            System.out.println("Medication: " + med.getName() + 
                             " (" + med.getDosage() + ")" +
                             " - Qty: " + med.getQuantityLeft() + 
                             "/" + med.getMonthlyRequirement() +
                             (med.isRefillNeeded() ? " [NEEDS REFILL]" : ""));
        }
        
        // Check if resident needs refill
        System.out.println("\n2. Refill Status:");
        System.out.println("Resident 1 needs refill: " + resident1.needsRefill());
        System.out.println("Resident 3 needs refill: " + resident3.needsRefill());
        
        // Add a new medication
        System.out.println("\n3. Adding New Medication:");
        Medication newMed = new Medication("Vitamin D", "1000IU daily", 30, 30);
        resident1.addMedication(newMed);
        System.out.println("Added new medication: " + newMed.getName() + " for " + resident1.getName());
        
        // Remove a medication
        System.out.println("\n4. Removing Medication:");
        boolean removed = resident1.removeMedication("Lisinopril");
        System.out.println("Medication Lisinopril removed: " + removed);
        
        // Display updated medication list
        System.out.println("\n5. Updated Medications for Resident 1:");
        for (Medication med : resident1.getMedications()) {
            System.out.println("Medication: " + med.getName() + 
                             " (" + med.getDosage() + ")" +
                             " - Qty: " + med.getQuantityLeft() + 
                             "/" + med.getMonthlyRequirement() +
                             (med.isRefillNeeded() ? " [NEEDS REFILL]" : ""));
        }
    }
    
    // Test Case 5: Doctor Visits
    public static void runDoctorVisitTest(TestDataInitializer initializer) {
        System.out.println("\n=== TEST CASE 5: DOCTOR VISITS ===");
        
        Doctor doctor1 = initializer.getDoctor1();
        Doctor doctor2 = initializer.getDoctor2();
        Resident resident1 = initializer.getResident1();
        Resident resident2 = initializer.getResident2();
        WardEmployee emp1 = initializer.getEmp1();
        
        // Create a new doctor visit
        System.out.println("\n1. Logging a New Doctor Visit:");
        DoctorVisit newVisit = new DoctorVisit(
            LocalDate.of(2023, 11, 1), 
            "Follow-up appointment, condition improving", 
            doctor1, 
            resident1
        );
        newVisit.addPrescription("Continue Metformin");
        newVisit.addPrescription("Add Calcium supplement");
        
        emp1.logDoctorVisit(resident1, newVisit);
        
        // View resident's doctor visit history
        System.out.println("\n2. Doctor Visit History for Resident 1:");
        for (DoctorVisit visit : resident1.getDoctorVisits()) {
            System.out.println("Visit Date: " + visit.getDate() + 
                             ", Doctor: " + visit.getDoctor().getName() +
                             ", Notes: " + visit.getNotes());
            System.out.println("  Prescriptions: " + String.join(", ", visit.getPrescriptions()));
        }
        
        // View doctor's visit history
        System.out.println("\n3. Doctor 1's Visit History:");
        List<DoctorVisit> doctorVisits = doctor1.viewResidentHistory("RES001");
        for (DoctorVisit visit : doctorVisits) {
            System.out.println("Visit Date: " + visit.getDate() + 
                             ", Resident: " + visit.getResident().getName() +
                             ", Notes: " + visit.getNotes());
        }
    }
    
    // Test Case 6: Login History
    public static void runLoginHistoryTest(TestDataInitializer initializer) {
        System.out.println("\n=== TEST CASE 6: LOGIN HISTORY ===");
        
        Admin admin = initializer.getAdmin();
        WardEmployee emp1 = initializer.getEmp1();
        
        // Simulate some login/logout activity
        System.out.println("\n1. Simulating Login/Logout Activity:");
        
        // Admin login and logout
        admin.login("admin@example.com", "admin123");
        try { Thread.sleep(100); } catch (InterruptedException e) {} // Simulate some activity
        admin.logout();
        
        // Employee login and logout
        emp1.login("emp1@example.com", "emp123");
        try { Thread.sleep(150); } catch (InterruptedException e) {} // Simulate some activity
        emp1.logout();
        
        // View login history
        System.out.println("\n2. Admin Login History:");
        for (LoginEntry entry : LoginLogger.getInstance().getUserLogHistory("ADMIN001")) {
            System.out.println("Login: " + entry.getLoginTime() + 
                             ", Logout: " + entry.getLogoutTime() +
                             ", Duration: " + entry.duration().toSeconds() + " seconds");
        }
        
        System.out.println("\n3. Employee Login History:");
        for (LoginEntry entry : LoginLogger.getInstance().getUserLogHistory("EMP001")) {
            System.out.println("Login: " + entry.getLoginTime() + 
                             ", Logout: " + entry.getLogoutTime() +
                             ", Duration: " + entry.duration().toSeconds() + " seconds");
        }
    }
}

// Helper class to initialize test data
class TestDataInitializer {
    private Admin admin;
    private Ward generalWard;
    private Ward specialWard;
    private WardEmployee emp1;
    private WardEmployee emp2;
    private Resident resident1;
    private Resident resident2;
    private Resident resident3;
    private Doctor doctor1;
    private Doctor doctor2;
    
    public void initializeTestData() {
        // Create admin
        admin = new Admin("ADMIN001", "System Administrator", "admin@example.com", "admin123");
        
        // Create wards
        generalWard = new Ward("WARD001", "General Care");
        specialWard = new Ward("WARD002", "Special Care");
        
        // Add wards to admin
        admin.addWard(generalWard);
        admin.addWard(specialWard);
        
        // Create employees
        emp1 = new WardEmployee("EMP001", "John Doe", "emp1@example.com", "emp123");
        emp2 = new WardEmployee("EMP002", "Jane Smith", "emp2@example.com", "emp456");
        
        // Assign employees to wards
        generalWard.addEmployee(emp1);
        specialWard.addEmployee(emp2);
        
        // Create residents
        resident1 = new Resident("RES001", "Robert Brown", 75, "Arthritis", "Low Sodium");
        resident2 = new Resident("RES002", "Mary Johnson", 82, "Diabetes", "Diabetic");
        resident3 = new Resident("RES003", "William Davis", 78, "Hypertension", "Low Fat");
        
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
        doctor1 = new Doctor("DOC001", "Dr. Sarah Wilson", "Geriatrics");
        doctor2 = new Doctor("DOC002", "Dr. Michael Chen", "Endocrinology");
        
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
    }
    
    // Getters for test data
    public Admin getAdmin() { return admin; }
    public Ward getGeneralWard() { return generalWard; }
    public Ward getSpecialWard() { return specialWard; }
    public WardEmployee getEmp1() { return emp1; }
    public WardEmployee getEmp2() { return emp2; }
    public Resident getResident1() { return resident1; }
    public Resident getResident2() { return resident2; }
    public Resident getResident3() { return resident3; }
    public Doctor getDoctor1() { return doctor1; }
    public Doctor getDoctor2() { return doctor2; }
}
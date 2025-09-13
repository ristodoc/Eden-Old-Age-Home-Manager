package eden.oldagehome;

import eden.oldagehome.entity.Admin;
import eden.oldagehome.entity.WardEmployee;

public class OldAgeHomeApp {
    public static void main(String[] args) {
        System.out.println("=== Old Age Home Management System ===");
        
        try {
            // Test admin login
            System.out.println("\n1. Testing Admin Login:");
            Admin admin = new Admin();
            boolean loginSuccess = admin.login("admin@example.com", "admin123");
            
            if (loginSuccess) {
                System.out.println("Admin login successful! Welcome " + admin.getName());
                
                // Test viewing all wards
                System.out.println("\n2. Testing View All Wards:");
                admin.viewAllWards().forEach(ward -> {
                    System.out.println("Ward: " + ward.getName() + " (ID: " + ward.getWardId() + ")");
                    System.out.println("  Patients: " + ward.getPatients().size());
                    System.out.println("  Employees: " + ward.getEmployees().size());
                });
                
                // Test generating report
                System.out.println("\n3. Testing Report Generation:");
                System.out.println(admin.generateReport());
                
                // Test budget management
                System.out.println("\n4. Testing Budget Management:");
                admin.manageBudget(5000.0);
                System.out.println("Current budget: " + admin.getTotalBudget());
                
                // Test admin logout
                admin.logout();
                System.out.println("Admin logged out successfully");
            } else {
                System.out.println("Admin login failed.");
            }
            
            // Test ward employee login
            System.out.println("\n5. Testing Ward Employee Login:");
            WardEmployee employee = new WardEmployee();
            boolean empLoginSuccess = employee.login("emp1@example.com", "emp123");
            
            if (empLoginSuccess) {
                System.out.println("Employee login successful! Welcome " + employee.getName());
                
                // Test viewing assigned patients
                System.out.println("\n6. Testing View Assigned Patients:");
                employee.viewAssignedPatients().forEach(patient -> {
                    System.out.println("Patient: " + patient.getName() + " (Age: " + patient.getAge() + ")");
                });
                
                // Test generating refill list
                System.out.println("\n7. Testing Medication Refill List:");
                employee.generateMonthlyRefillList().forEach(med -> {
                    System.out.println("Medication: " + med.getName() + " - Qty: " + 
                                      med.getQuantityLeft() + "/" + med.getMonthlyRequirement());
                });
                
                // Test employee logout
                employee.logout();
                System.out.println("Employee logged out successfully");
            } else {
                System.out.println("Employee login failed.");
            }
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== Application Test Completed ===");
    }
}
package eden.oldagehome.entity;


import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<Ward> wards;
    private double totalBudget;
    
    public Admin(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.wards = new ArrayList<>();
        this.totalBudget = 10000.00; // Default budget
    }
    
    public List<Ward> viewAllWards() {
        return new ArrayList<>(wards);
    }
    
    public boolean manageBudget(double amount) {
        this.totalBudget += amount;
        System.out.println("Budget updated. New total: $" + this.totalBudget);
        return true;
    }
    
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Old Age Home Management System Report\n");
        report.append("=====================================\n\n");
        
        for (Ward ward : wards) {
            report.append("Ward: ").append(ward.getName()).append("\n");
            report.append("Number of Patients: ").append(ward.getPatients().size()).append("\n");
            report.append("Monthly Expenses: $").append(ward.calculateMonthlyExpenses()).append("\n");
            report.append("-------------------------------------\n");
        }
        
        return report.toString();
    }
    
    public void addWard(Ward ward) {
        this.wards.add(ward);
    }
    
    // Getters and setters
    public List<Ward> getWards() { return wards; }
    public void setWards(List<Ward> wards) { this.wards = wards; }
    public double getTotalBudget() { return totalBudget; }
    public void setTotalBudget(double totalBudget) { this.totalBudget = totalBudget; }
    
    @Override
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Admin login successful! Welcome " + this.name);
            LoginLogger.getInstance().recordLogin(this.userId);
            return true;
        }
        return false;
    }
    
    @Override
    public void logout() {
        LoginLogger.getInstance().recordLogout(this.userId);
        System.out.println("Admin logged out successfully");
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

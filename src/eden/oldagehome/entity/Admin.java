package eden.oldagehome.entity;

import java.util.List;

public class Admin extends User {
    private List<Ward> wards;
    private double totalBudget;

    public List<Ward> viewAllWards() {
        // Implementation to fetch all wards from database
        return null;
    }

    public boolean manageBudget(double amount) {
        // Implementation to manage budget
        this.totalBudget += amount;
        return true;
    }

    public String generateReport() {
        // Implementation to generate report
        return "Report generated";
    }

    // Getters and setters
    public List<Ward> getWards() { return wards; }
    public void setWards(List<Ward> wards) { this.wards = wards; }
    public double getTotalBudget() { return totalBudget; }
    public void setTotalBudget(double totalBudget) { this.totalBudget = totalBudget; }

    @Override
    public boolean login(String email, String password) {
        // Implementation to verify admin login
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
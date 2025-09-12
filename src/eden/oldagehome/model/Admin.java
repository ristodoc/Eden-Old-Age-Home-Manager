package eden.oldagehome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Admin user, inheriting from the User class.
 */
public class Admin extends User {

    private List<Ward> wards;
    private double totalBudget;

    public Admin(String userId, String name, String email, String password, double totalBudget) {
        super(userId, name, email, password);
        this.totalBudget = totalBudget;
        this.wards = new ArrayList<>();
    }

    // Admin-specific methods

    public void viewAllWards() {
        System.out.println("Viewing all wards:");
        for (Ward ward : wards) {
            System.out.println(ward);
        }
    }

    public void manageBudget() {
        System.out.println("Current total budget is: " + this.totalBudget);
        // Logic to manage budget would go here
    }

    public void generateReport() {
        System.out.println("Generating a system-wide report...");
        // Logic for report generation would go here
    }

    // Implementation of abstract methods from User class

    @Override
    public void login() {
        System.out.println("Admin " + this.name + " logged in.");
        // In a real app, this would involve authentication against the database
    }

    @Override
    public void logout() {
        System.out.println("Admin " + this.name + " logged out.");
        // Logic to handle session termination
    }

    @Override
    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
        System.out.println("Admin " + this.name + " changed password.");
        // Logic to update password in the database
    }

    // Getters and Setters

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
}

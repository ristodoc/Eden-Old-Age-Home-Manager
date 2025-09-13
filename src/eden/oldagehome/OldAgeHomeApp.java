package eden.oldagehome;

import eden.oldagehome.service.AuthService;
import eden.oldagehome.entity.User;

public class OldAgeHomeApp {
    public static void main(String[] args) {
        // Example usage
        AuthService authService = new AuthService();

        // Login example
        User user = authService.login("admin@example.com", "password123");

        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName());

            // Perform other operations based on user type

            // Logout
            authService.logout(user.getUserId());
            System.out.println("Logged out successfully");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }
}
package eden.oldagehome.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages login and logout records.
 * Note: In a real application, this class would be a service that interacts
 * directly with the database rather than holding a list in memory.
 */
public class LoginLogger {

    private List<LoginEntry> logs;

    public LoginLogger() {
        this.logs = new ArrayList<>();
    }

    /**
     * Records a new login event.
     * @param userId The ID of the user logging in.
     */
    public void recordLogin(String userId) {
        // In a real app, this would insert a new record into the login_history table.
        LoginEntry newEntry = new LoginEntry(userId, LocalDateTime.now());
        logs.add(newEntry);
        System.out.println("Login recorded for user: " + userId);
    }

    /**
     * Records a logout event for the last login of a user.
     * @param userId The ID of the user logging out.
     */
    public void recordLogout(String userId) {
        // In a real app, this would update the logout_time for the last entry of the user.
        for (int i = logs.size() - 1; i >= 0; i--) {
            LoginEntry entry = logs.get(i);
            if (entry.getUserId().equals(userId) && entry.getLogoutTime() == null) {
                entry.setLogoutTime(LocalDateTime.now());
                System.out.println("Logout recorded for user: " + userId);
                return;
            }
        }
    }

    /**
     * Retrieves the login history for a specific user.
     * @param userId The ID of the user.
     * @return A list of LoginEntry objects for the user.
     */
    public List<LoginEntry> getUserLogHistory(String userId) {
        // In a real app, this would be a SELECT query on the login_history table.
        return logs.stream()
                .filter(log -> log.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<LoginEntry> getLogs() {
        return logs;
    }
}

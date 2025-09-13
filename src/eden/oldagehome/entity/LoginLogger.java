package eden.oldagehome.entity;

import java.time.LocalDateTime;
import java.util.List;

public class LoginLogger {
    private List<LoginEntry> logs;

    public void recordLogin(String userId) {
        // Implementation to record login
        LoginEntry entry = new LoginEntry();
        entry.setUserId(userId);
        entry.setLoginTime(LocalDateTime.now());
        logs.add(entry);
    }

    public void recordLogout(String userId) {
        // Implementation to record logout
        logs.stream()
                .filter(entry -> entry.getUserId().equals(userId) && entry.getLogoutTime() == null)
                .findFirst()
                .ifPresent(entry -> entry.setLogoutTime(LocalDateTime.now()));
    }

    public List<LoginEntry> getUserLogHistory(String userId) {
        // Implementation to get user log history
        return logs.stream()
                .filter(entry -> entry.getUserId().equals(userId))
                .toList();
    }

    // Getters and setters
    public List<LoginEntry> getLogs() { return logs; }
    public void setLogs(List<LoginEntry> logs) { this.logs = logs; }
}
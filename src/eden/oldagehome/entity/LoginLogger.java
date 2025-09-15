package eden.oldagehome.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginLogger {
    private static LoginLogger instance;
    private List<LoginEntry> logs;
    
    private LoginLogger() {
        this.logs = new ArrayList<>();
    }
    
    public static LoginLogger getInstance() {
        if (instance == null) {
            instance = new LoginLogger();
        }
        return instance;
    }
    
    public void recordLogin(String userId) {
        LoginEntry entry = new LoginEntry();
        entry.setUserId(userId);
        entry.setLoginTime(LocalDateTime.now());
        logs.add(entry);
    }
    
    public void recordLogout(String userId) {
        for (int i = logs.size() - 1; i >= 0; i--) {
            LoginEntry entry = logs.get(i);
            if (entry.getUserId().equals(userId) && entry.getLogoutTime() == null) {
                entry.setLogoutTime(LocalDateTime.now());
                return;
            }
        }
    }
    
    public List<LoginEntry> getUserLogHistory(String userId) {
        List<LoginEntry> userLogs = new ArrayList<>();
        for (LoginEntry entry : logs) {
            if (entry.getUserId().equals(userId)) {
                userLogs.add(entry);
            }
        }
        return userLogs;
    }
    
    // Getters and setters
    public List<LoginEntry> getLogs() { return logs; }
    public void setLogs(List<LoginEntry> logs) { this.logs = logs; }
}

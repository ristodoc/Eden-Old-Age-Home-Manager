package eden.oldagehome.entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class LoginEntry {
    private String userId;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    public Duration duration() {
        if (logoutTime != null) {
            return Duration.between(loginTime, logoutTime);
        }
        return Duration.between(loginTime, LocalDateTime.now());
    }

    // Getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public LocalDateTime getLoginTime() { return loginTime; }
    public void setLoginTime(LocalDateTime loginTime) { this.loginTime = loginTime; }
    public LocalDateTime getLogoutTime() { return logoutTime; }
    public void setLogoutTime(LocalDateTime logoutTime) { this.logoutTime = logoutTime; }
}
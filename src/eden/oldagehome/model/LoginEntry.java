package eden.oldagehome.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a single login/logout entry for a user.
 */
public class LoginEntry {

    private String userId;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    public LoginEntry(String userId, LocalDateTime loginTime) {
        this.userId = userId;
        this.loginTime = loginTime;
    }

    /**
     * Calculates the duration of the session.
     * @return Duration between login and logout. Returns null if logout time is not set.
     */
    public Duration duration() {
        if (this.logoutTime != null) {
            return Duration.between(loginTime, logoutTime);
        }
        return null;
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }
}

package eden.oldagehome.entity;

import java.time.LocalDateTime;
import java.util.List;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;

    public abstract boolean login(String email, String password);
    public abstract void logout();
    public abstract boolean changePassword(String oldPassword, String newPassword);

    // Getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
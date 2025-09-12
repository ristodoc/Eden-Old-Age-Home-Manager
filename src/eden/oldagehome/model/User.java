package eden.oldagehome.model;

/**
 * Abstract User class representing a generic user of the system.
 */
public abstract class User {

    protected String userId;
    protected String name;
    protected String email;
    protected String password; // In a real application, this should be securely stored (e.g., hashed)
    protected String role;

    public User(String userId, String name, String email, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void login();
    public abstract void logout();
    public abstract void changePassword(String newPassword);

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + ''' +
                ", name='" + name + ''' +
                ", email='" + email + ''' +
                '}';
    }
}
package eden.oldagehome.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User extends BaseEntity {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    
    public boolean login(String email, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                this.userId = rs.getString("user_id");
                this.name = rs.getString("name");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                
                // Record login
                LoginLogger logger = new LoginLogger();
                logger.recordLogin(this.userId);
                
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return false;
    }
    
    public void logout() {
        LoginLogger logger = new LoginLogger();
        logger.recordLogout(this.userId);
    }
    
    public boolean changePassword(String oldPassword, String newPassword) {
        if (!this.password.equals(oldPassword)) {
            return false;
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "UPDATE users SET password = ? WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, this.userId);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                this.password = newPassword;
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Password change error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return false;
    }
    
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
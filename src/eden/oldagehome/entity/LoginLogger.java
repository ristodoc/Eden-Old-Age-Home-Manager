package eden.oldagehome.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginLogger extends BaseEntity {
    
    public void recordLogin(String userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "INSERT INTO login_entries (user_id, login_time) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error recording login: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public void recordLogout(String userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "UPDATE login_entries SET logout_time = ? WHERE user_id = ? AND logout_time IS NULL ORDER BY login_time DESC LIMIT 1";
            stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(2, userId);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error recording logout: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public List<LoginEntry> getUserLogHistory(String userId) {
        List<LoginEntry> userLogs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM login_entries WHERE user_id = ? ORDER BY login_time DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                LoginEntry entry = new LoginEntry();
                entry.setUserId(rs.getString("user_id"));
                entry.setLoginTime(rs.getTimestamp("login_time").toLocalDateTime());
                
                Timestamp logoutTime = rs.getTimestamp("logout_time");
                if (logoutTime != null) {
                    entry.setLogoutTime(logoutTime.toLocalDateTime());
                }
                
                userLogs.add(entry);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching login history: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return userLogs;
    }
}
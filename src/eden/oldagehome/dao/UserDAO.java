package com.oldagehome.dao;

import eden.oldagehome.entity.User;
import eden.oldagehome.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User getUserById(String userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("user_type");

                if ("ADMIN".equals(userType)) {
                    user = new Admin();
                } else if ("WARD_EMPLOYEE".equals(userType)) {
                    user = new WardEmployee();
                }

                if (user != null) {
                    user.setUserId(rs.getString("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User login(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("user_type");

                if ("ADMIN".equals(userType)) {
                    user = new Admin();
                } else if ("WARD_EMPLOYEE".equals(userType)) {
                    user = new WardEmployee();
                }

                if (user != null) {
                    user.setUserId(rs.getString("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean changePassword(String userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, userId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
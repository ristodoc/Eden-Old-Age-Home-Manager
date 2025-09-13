package eden.oldagehome.service;

import eden.oldagehome.dao.UserDAO;
import eden.oldagehome.entity.User;
import eden.oldagehome.entity.LoginLogger;

public class AuthService {
    private UserDAO userDAO;
    private LoginLogger loginLogger;

    public AuthService() {
        this.userDAO = new UserDAO();
        this.loginLogger = new LoginLogger();
    }

    public User login(String email, String password) {
        User user = userDAO.login(email, password);
        if (user != null) {
            loginLogger.recordLogin(user.getUserId());
        }
        return user;
    }

    public void logout(String userId) {
        loginLogger.recordLogout(userId);
    }

    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        // Verify old password first
        User user = userDAO.getUserById(userId);
        if (user != null && user.getPassword().equals(oldPassword)) {
            return userDAO.changePassword(userId, newPassword);
        }
        return false;
    }
}
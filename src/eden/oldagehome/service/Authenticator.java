package eden.oldagehome.service;

import eden.oldagehome.model.User;
import eden.oldagehome.repository.UserRepository;
import eden.oldagehome.util.PasswordUtils;

public class Authenticator {

    private final UserRepository userRepository;

    public Authenticator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a user by their ID and password.
     *
     * @param id       The user ID.
     * @param password The user's password.
     * @return An AuthenticationResult indicating success and the user's role.
     */
    public AuthenticationResult authenticate(String id, String password) {
        // Fetch user details from the repository
        User user = userRepository.findById(id);

        if (user != null && PasswordUtils.verifyPassword(password, user.getPassword())) {
            // Authentication successful
            return new AuthenticationResult(true, user.getRole());
        }

        // Authentication failed
        return new AuthenticationResult(false, null);
    }

    public static class AuthenticationResult {
        private final boolean success;
        private final String role;

        public AuthenticationResult(boolean success, String role) {
            this.success = success;
            this.role = role;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getRole() {
            return role;
        }
    }
}

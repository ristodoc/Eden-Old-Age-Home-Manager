package eden.oldagehome.util;

public class PasswordUtils {

    public static String hashPassword(String password) {
        // Password hashing is removed. Storing password in plain text.
        return password;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        // Simple string comparison for plain text passwords.
        return password.equals(hashedPassword);
    }
}

package eden.oldagehome.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/old_age_home?useSSL=false&serverTimezone=UTC";
    private static final String USER = "oa_user";
    private static final String PASS = "secret_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
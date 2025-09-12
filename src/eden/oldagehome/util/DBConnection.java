package eden.oldagehome.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection is a utility class for managing the database connection.
 */
public class DBConnection {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Replace "eden_db" with your actual database name
    private static final String DB_URL = "jdbc:mysql://localhost:3306/eden_db?useSSL=false&serverTimezone=UTC";

    // Database credentials - It's recommended to use a more secure way to store credentials
    private static final String USER = "root"; // replace with your db username
    private static final String PASS = "password"; // replace with your db password

    // Private constructor to prevent instantiation
    private DBConnection() {}

    /**
     * Returns a new database connection.
     *
     * @return A Connection object to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}
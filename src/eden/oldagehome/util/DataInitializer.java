package eden.oldagehome.util;

import eden.oldagehome.model.Resident;
import eden.oldagehome.model.User;
import eden.oldagehome.model.Admin;
import eden.oldagehome.model.WardEmployee;
import eden.oldagehome.model.Ward;
import eden.oldagehome.repository.ResidentRepository;
import eden.oldagehome.repository.ResidentRepositoryImpl;
import eden.oldagehome.repository.UserRepository;
import eden.oldagehome.repository.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInitializer {
    
    private DBConnection dbConnection;
    private UserRepository userRepository;
    private ResidentRepository residentRepository;
    
    public DataInitializer() {
        this.dbConnection = new DBConnection();
        this.userRepository = new UserRepositoryImpl();
        this.residentRepository = new ResidentRepositoryImpl();
    }
    
    public void initializeSampleData() {
        try {
            initializeUsers();
            initializeWards();
            initializeResidents();
            System.out.println("Sample data initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initializeUsers() throws SQLException {
        // Create sample admin user
        String adminPassword = PasswordUtils.hashPassword("admin123");
        String insertAdmin = "INSERT INTO users (user_id, name, email, password, user_type) VALUES (?, ?, ?, ?, ?) " +
                           "ON DUPLICATE KEY UPDATE name=VALUES(name), email=VALUES(email), password=VALUES(password)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertAdmin)) {
            
            stmt.setString(1, "admin001");
            stmt.setString(2, "Jane Smith");
            stmt.setString(3, "jane.smith@eden.com");
            stmt.setString(4, adminPassword);
            stmt.setString(5, "admin");
            stmt.executeUpdate();
        }
        
        // Create sample ward employee
        String employeePassword = PasswordUtils.hashPassword("employee123");
        String insertEmployee = "INSERT INTO users (user_id, name, email, password, user_type) VALUES (?, ?, ?, ?, ?) " +
                              "ON DUPLICATE KEY UPDATE name=VALUES(name), email=VALUES(email), password=VALUES(password)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertEmployee)) {
            
            stmt.setString(1, "emp001");
            stmt.setString(2, "John Doe");
            stmt.setString(3, "john.doe@eden.com");
            stmt.setString(4, employeePassword);
            stmt.setString(5, "ward_employee");
            stmt.executeUpdate();
        }
        
        // Create ward employee record
        String insertWardEmployee = "INSERT INTO ward_employees (employee_id, ward_id) VALUES (?, ?) " +
                                  "ON DUPLICATE KEY UPDATE ward_id=VALUES(ward_id)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertWardEmployee)) {
            
            stmt.setString(1, "emp001");
            stmt.setString(2, "ward001");
            stmt.executeUpdate();
        }
    }
    
    private void initializeWards() throws SQLException {
        String insertWard = "INSERT INTO wards (ward_id, name) VALUES (?, ?) " +
                          "ON DUPLICATE KEY UPDATE name=VALUES(name)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertWard)) {
            
            stmt.setString(1, "ward001");
            stmt.setString(2, "Garden View Ward");
            stmt.executeUpdate();
            
            stmt.setString(1, "ward002");
            stmt.setString(2, "Sunset Wing");
            stmt.executeUpdate();
            
            stmt.setString(1, "ward003");
            stmt.setString(2, "Memory Care Unit");
            stmt.executeUpdate();
        }
    }
    
    private void initializeResidents() throws SQLException {
        // Sample residents data
        String[][] residentsData = {
            {"RES001", "Margaret Johnson", "78", "Diabetes", "Low Sugar", "ward001"},
            {"RES002", "Robert Williams", "82", "Hypertension", "Low Sodium", "ward001"},
            {"RES003", "Alice Brown", "85", "Arthritis", "Anti-inflammatory", "ward002"},
            {"RES004", "James Davis", "79", "Heart Condition", "Heart Healthy", "ward002"},
            {"RES005", "Mary Wilson", "88", "Dementia", "Brain Healthy", "ward003"},
            {"RES006", "Charles Miller", "76", "Diabetes", "Low Sugar", "ward001"},
            {"RES007", "Dorothy Garcia", "83", "Osteoporosis", "High Calcium", "ward002"},
            {"RES008", "Thomas Martinez", "81", "Hypertension", "Low Sodium", "ward003"},
            {"RES009", "Helen Anderson", "87", "Arthritis", "Anti-inflammatory", "ward001"},
            {"RES010", "William Taylor", "84", "Heart Condition", "Heart Healthy", "ward002"}
        };
        
        String insertResident = "INSERT INTO residents (resident_id, name, age, disease, diet, ward_id) VALUES (?, ?, ?, ?, ?, ?) " +
                              "ON DUPLICATE KEY UPDATE name=VALUES(name), age=VALUES(age), disease=VALUES(disease), diet=VALUES(diet), ward_id=VALUES(ward_id)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertResident)) {
            
            for (String[] resident : residentsData) {
                stmt.setString(1, resident[0]);
                stmt.setString(2, resident[1]);
                stmt.setInt(3, Integer.parseInt(resident[2]));
                stmt.setString(4, resident[3]);
                stmt.setString(5, resident[4]);
                stmt.setString(6, resident[5]);
                stmt.executeUpdate();
            }
        }
        
        // Add some sample medications
        initializeMedications();
    }
    
    private void initializeMedications() throws SQLException {
        String insertMedication = "INSERT INTO medications (resident_id, name, dosage, quantity_left, monthly_requirement) VALUES (?, ?, ?, ?, ?) " +
                                "ON DUPLICATE KEY UPDATE dosage=VALUES(dosage), quantity_left=VALUES(quantity_left), monthly_requirement=VALUES(monthly_requirement)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertMedication)) {
            
            // Sample medications for different residents
            stmt.setString(1, "RES001");
            stmt.setString(2, "Metformin");
            stmt.setString(3, "500mg");
            stmt.setInt(4, 15); // Low quantity - needs attention
            stmt.setInt(5, 30);
            stmt.executeUpdate();
            
            stmt.setString(1, "RES002");
            stmt.setString(2, "Lisinopril");
            stmt.setString(3, "10mg");
            stmt.setInt(4, 25);
            stmt.setInt(5, 30);
            stmt.executeUpdate();
            
            stmt.setString(1, "RES003");
            stmt.setString(2, "Ibuprofen");
            stmt.setString(3, "400mg");
            stmt.setInt(4, 5); // Very low quantity - needs attention
            stmt.setInt(5, 30);
            stmt.executeUpdate();
            
            stmt.setString(1, "RES004");
            stmt.setString(2, "Atorvastatin");
            stmt.setString(3, "20mg");
            stmt.setInt(4, 28);
            stmt.setInt(5, 30);
            stmt.executeUpdate();
            
            stmt.setString(1, "RES005");
            stmt.setString(2, "Donepezil");
            stmt.setString(3, "5mg");
            stmt.setInt(4, 8); // Low quantity - needs attention
            stmt.setInt(5, 30);
            stmt.executeUpdate();
        }
    }
    
    public static void main(String[] args) {
        DataInitializer initializer = new DataInitializer();
        initializer.initializeSampleData();
    }
}

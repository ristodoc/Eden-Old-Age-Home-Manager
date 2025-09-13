package eden.oldagehome.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardEmployee extends User {
    private String employeeId;
    private Ward assignedWard;
    
    public List<Resident> viewAssignedPatients() {
        if (assignedWard != null) {
            return assignedWard.getPatients();
        }
        return new ArrayList<>();
    }
    
    public boolean updatePatientInfo(Resident p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "UPDATE residents SET name = ?, age = ?, disease = ?, diet = ? WHERE resident_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getAge());
            stmt.setString(3, p.getDisease());
            stmt.setString(4, p.getDiet());
            stmt.setString(5, p.getResidentId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating patient info: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public List<Medication> generateMonthlyRefillList() {
        List<Medication> medications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM medications WHERE quantity_left < (monthly_requirement * 0.2)";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Medication medication = new Medication();
                medication.setName(rs.getString("name"));
                medication.setDosage(rs.getString("dosage"));
                medication.setQuantityLeft(rs.getInt("quantity_left"));
                medication.setMonthlyRequirement(rs.getInt("monthly_requirement"));
                
                medications.add(medication);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching medications needing refill: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return medications;
    }
    
    public boolean logDoctorVisit(Resident p, DoctorVisit v) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "INSERT INTO doctor_visits (resident_id, doctor_id, visit_date, notes) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getResidentId());
            stmt.setString(2, v.getDoctor().getDoctorId());
            stmt.setDate(3, java.sql.Date.valueOf(v.getDate()));
            stmt.setString(4, v.getNotes());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error logging doctor visit: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public List<LoginEntry> viewLoginHistory() {
        LoginLogger logger = new LoginLogger();
        return logger.getUserLogHistory(this.userId);
    }
    
    // Getters and setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public Ward getAssignedWard() { return assignedWard; }
    public void setAssignedWard(Ward assignedWard) { this.assignedWard = assignedWard; }
}
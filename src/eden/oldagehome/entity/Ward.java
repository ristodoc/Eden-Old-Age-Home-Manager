package eden.oldagehome.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Ward extends BaseEntity {
    private String wardId;
    private String name;
    private List<Resident> patients;
    private List<WardEmployee> employees;
    
    public boolean addPatient(Resident p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "INSERT INTO residents (resident_id, name, age, disease, diet, ward_id) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getResidentId());
            stmt.setString(2, p.getName());
            stmt.setInt(3, p.getAge());
            stmt.setString(4, p.getDisease());
            stmt.setString(5, p.getDiet());
            stmt.setString(6, this.wardId);
            
            boolean success = stmt.executeUpdate() > 0;
            
            if (success) {
                this.patients.add(p);
            }
            
            return success;
        } catch (SQLException e) {
            System.err.println("Error adding patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public boolean removePatient(String patientId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "DELETE FROM residents WHERE resident_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            
            boolean success = stmt.executeUpdate() > 0;
            
            if (success) {
                this.patients.removeIf(p -> p.getResidentId().equals(patientId));
            }
            
            return success;
        } catch (SQLException e) {
            System.err.println("Error removing patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public String getMedicationSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Medication Summary for Ward: ").append(this.name).append("\n");
        summary.append("============================================\n");
        
        for (Resident resident : patients) {
            summary.append("Resident: ").append(resident.getName()).append("\n");
            
            if (resident.getMedications().isEmpty()) {
                summary.append("  No medications prescribed\n");
            } else {
                for (Medication medication : resident.getMedications()) {
                    summary.append("  - ").append(medication.getName())
                           .append(" (").append(medication.getDosage()).append(")")
                           .append(" - Qty: ").append(medication.getQuantityLeft())
                           .append("/").append(medication.getMonthlyRequirement())
                           .append(medication.isRefillNeeded() ? " [NEEDS REFILL]" : "")
                           .append("\n");
                }
            }
            summary.append("--------------------------------------------\n");
        }
        
        return summary.toString();
    }
    
    public double calculateMonthlyExpenses() {
        double expenses = 0.0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT SUM(m.monthly_requirement * 10) as total_expenses " +
                         "FROM residents r " +
                         "JOIN medications m ON r.resident_id = m.resident_id " +
                         "WHERE r.ward_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.wardId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                expenses = rs.getDouble("total_expenses");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating monthly expenses: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return expenses;
    }
    
    // Getters and setters
    public String getWardId() { return wardId; }
    public void setWardId(String wardId) { this.wardId = wardId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Resident> getPatients() { return patients; }
    public void setPatients(List<Resident> patients) { this.patients = patients; }
    public List<WardEmployee> getEmployees() { return employees; }
    public void setEmployees(List<WardEmployee> employees) { this.employees = employees; }
}
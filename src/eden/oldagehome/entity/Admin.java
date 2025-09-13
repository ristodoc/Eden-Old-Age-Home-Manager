package eden.oldagehome.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<Ward> wards;
    private double totalBudget;
    
    public List<Ward> viewAllWards() {
        List<Ward> allWards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM wards";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Ward ward = new Ward();
                ward.setWardId(rs.getString("ward_id"));
                ward.setName(rs.getString("name"));
                
                // Load patients for this ward
                ward.setPatients(getPatientsByWardId(ward.getWardId()));
                
                // Load employees for this ward
                ward.setEmployees(getEmployeesByWardId(ward.getWardId()));
                
                allWards.add(ward);
            }
            
            this.wards = allWards;
        } catch (SQLException e) {
            System.err.println("Error fetching all wards: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return allWards;
    }
    
    public boolean manageBudget(double amount) {
        this.totalBudget += amount;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            String sql = "UPDATE admin SET total_budget = ? WHERE admin_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, this.totalBudget);
            stmt.setString(2, this.userId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error managing budget: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Old Age Home Management System Report\n");
        report.append("=====================================\n\n");
        
        List<Ward> allWards = viewAllWards();
        
        for (Ward ward : allWards) {
            report.append("Ward: ").append(ward.getName()).append("\n");
            report.append("Number of Patients: ").append(ward.getPatients().size()).append("\n");
            report.append("Monthly Expenses: $").append(calculateMonthlyExpenses(ward.getWardId())).append("\n");
            report.append("-------------------------------------\n");
        }
        
        return report.toString();
    }
    
    private double calculateMonthlyExpenses(String wardId) {
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
            stmt.setString(1, wardId);
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
    
    private List<Resident> getPatientsByWardId(String wardId) {
        List<Resident> patients = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM residents WHERE ward_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, wardId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Resident resident = new Resident();
                resident.setResidentId(rs.getString("resident_id"));
                resident.setName(rs.getString("name"));
                resident.setAge(rs.getInt("age"));
                resident.setDisease(rs.getString("disease"));
                resident.setDiet(rs.getString("diet"));
                
                patients.add(resident);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching patients for ward: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return patients;
    }
    
    private List<WardEmployee> getEmployeesByWardId(String wardId) {
        List<WardEmployee> employees = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT u.* FROM users u " +
                         "JOIN ward_employees we ON u.user_id = we.employee_id " +
                         "WHERE we.ward_id = ? AND u.user_type = 'WARD_EMPLOYEE'";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, wardId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WardEmployee employee = new WardEmployee();
                employee.setUserId(rs.getString("user_id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employee.setEmployeeId(rs.getString("user_id"));
                
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employees for ward: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return employees;
    }
    
    // Getters and setters
    public List<Ward> getWards() { return wards; }
    public void setWards(List<Ward> wards) { this.wards = wards; }
    public double getTotalBudget() { return totalBudget; }
    public void setTotalBudget(double totalBudget) { this.totalBudget = totalBudget; }
}
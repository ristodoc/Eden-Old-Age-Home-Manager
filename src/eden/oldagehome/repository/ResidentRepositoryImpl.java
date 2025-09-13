package eden.oldagehome.repository;

import eden.oldagehome.model.Resident;
import eden.oldagehome.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidentRepositoryImpl implements ResidentRepository {
    
    private final DBConnection dbConnection;
    
    public ResidentRepositoryImpl() {
        this.dbConnection = new DBConnection();
    }
    
    @Override
    public Resident findById(String residentId) {
        String sql = "SELECT * FROM residents WHERE resident_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, residentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToResident(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public List<Resident> findAll() {
        List<Resident> residents = new ArrayList<>();
        String sql = "SELECT * FROM residents ORDER BY name";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                residents.add(mapResultSetToResident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return residents;
    }
    
    @Override
    public List<Resident> findByWardId(String wardId) {
        List<Resident> residents = new ArrayList<>();
        String sql = "SELECT * FROM residents WHERE ward_id = ? ORDER BY name";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, wardId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                residents.add(mapResultSetToResident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return residents;
    }
    
    @Override
    public boolean save(Resident resident) {
        String sql = "INSERT INTO residents (resident_id, name, age, disease, diet, ward_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, resident.getResidentId());
            stmt.setString(2, resident.getName());
            stmt.setInt(3, resident.getAge());
            stmt.setString(4, resident.getDisease());
            stmt.setString(5, resident.getDiet());
            stmt.setString(6, resident.getWardId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(Resident resident) {
        String sql = "UPDATE residents SET name = ?, age = ?, disease = ?, diet = ?, ward_id = ? WHERE resident_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, resident.getName());
            stmt.setInt(2, resident.getAge());
            stmt.setString(3, resident.getDisease());
            stmt.setString(4, resident.getDiet());
            stmt.setString(5, resident.getWardId());
            stmt.setString(6, resident.getResidentId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(String residentId) {
        String sql = "DELETE FROM residents WHERE resident_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, residentId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Resident> searchByName(String name) {
        List<Resident> residents = new ArrayList<>();
        String sql = "SELECT * FROM residents WHERE name LIKE ? ORDER BY name";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                residents.add(mapResultSetToResident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return residents;
    }
    
    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM residents";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    @Override
    public List<Resident> getResidentsNeedingAttention() {
        List<Resident> residents = new ArrayList<>();
        String sql = "SELECT DISTINCT r.* FROM residents r " +
                    "JOIN medications m ON r.resident_id = m.resident_id " +
                    "WHERE m.quantity_left <= m.monthly_requirement * 0.2";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                residents.add(mapResultSetToResident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return residents;
    }
    
    private Resident mapResultSetToResident(ResultSet rs) throws SQLException {
        Resident resident = new Resident(
            rs.getString("resident_id"),
            rs.getString("name"),
            rs.getInt("age"),
            rs.getString("disease"),
            rs.getString("diet")
        );
        resident.setWardId(rs.getString("ward_id"));
        return resident;
    }
}

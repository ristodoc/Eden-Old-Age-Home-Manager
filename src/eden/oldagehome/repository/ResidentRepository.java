package eden.oldagehome.repository;

import eden.oldagehome.model.Resident;
import java.util.List;

public interface ResidentRepository {
    
    /**
     * Find a resident by their ID
     * @param residentId The resident's ID
     * @return The resident if found, null otherwise
     */
    Resident findById(String residentId);
    
    /**
     * Find all residents
     * @return List of all residents
     */
    List<Resident> findAll();
    
    /**
     * Find residents by ward ID
     * @param wardId The ward ID
     * @return List of residents in the specified ward
     */
    List<Resident> findByWardId(String wardId);
    
    /**
     * Save a new resident
     * @param resident The resident to save
     * @return true if successful, false otherwise
     */
    boolean save(Resident resident);
    
    /**
     * Update an existing resident
     * @param resident The resident to update
     * @return true if successful, false otherwise
     */
    boolean update(Resident resident);
    
    /**
     * Delete a resident by ID
     * @param residentId The resident's ID
     * @return true if successful, false otherwise
     */
    boolean delete(String residentId);
    
    /**
     * Search residents by name
     * @param name The name to search for
     * @return List of residents matching the name
     */
    List<Resident> searchByName(String name);
    
    /**
     * Get total count of residents
     * @return Total number of residents
     */
    int getTotalCount();
    
    /**
     * Get residents needing attention (medication refills, etc.)
     * @return List of residents needing attention
     */
    List<Resident> getResidentsNeedingAttention();
}

package eden.oldagehome.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an activity or event in the old age home.
 */
public class Activity {
    
    private String activityId;
    private String name;
    private String description;
    private LocalDateTime scheduledDateTime;
    private String location;
    private int maxParticipants;
    private int currentParticipants;
    private String status; // Scheduled, Completed, Cancelled
    private String organizer;
    
    public Activity(String activityId, String name, String description, LocalDateTime scheduledDateTime, 
                   String location, int maxParticipants, String organizer) {
        this.activityId = activityId;
        this.name = name;
        this.description = description;
        this.scheduledDateTime = scheduledDateTime;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = 0;
        this.status = "Scheduled";
        this.organizer = organizer;
    }
    
    /**
     * Adds a participant to the activity
     * @return true if participant was added, false if activity is full
     */
    public boolean addParticipant() {
        if (currentParticipants < maxParticipants && "Scheduled".equals(status)) {
            currentParticipants++;
            return true;
        }
        return false;
    }
    
    /**
     * Removes a participant from the activity
     * @return true if participant was removed, false if no participants
     */
    public boolean removeParticipant() {
        if (currentParticipants > 0) {
            currentParticipants--;
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the activity is full
     * @return true if activity is full, false otherwise
     */
    public boolean isFull() {
        return currentParticipants >= maxParticipants;
    }
    
    /**
     * Checks if the activity is scheduled for today
     * @return true if activity is today, false otherwise
     */
    public boolean isToday() {
        LocalDateTime now = LocalDateTime.now();
        return scheduledDateTime.toLocalDate().equals(now.toLocalDate());
    }
    
    /**
     * Gets formatted date and time string
     * @return formatted date and time
     */
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a");
        return scheduledDateTime.format(formatter);
    }
    
    // Getters and Setters
    
    public String getActivityId() {
        return activityId;
    }
    
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }
    
    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getMaxParticipants() {
        return maxParticipants;
    }
    
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
    
    public int getCurrentParticipants() {
        return currentParticipants;
    }
    
    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getOrganizer() {
        return organizer;
    }
    
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
    
    @Override
    public String toString() {
        return "Activity{" +
                "activityId='" + activityId + '\'' +
                ", name='" + name + '\'' +
                ", scheduledDateTime=" + scheduledDateTime +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

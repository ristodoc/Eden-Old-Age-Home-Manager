package eden.oldagehome.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a report in the old age home management system.
 */
public class Report {
    
    private String reportId;
    private String reportType; // Occupancy, Medication, Staff, Financial, Activities, Health
    private String title;
    private String description;
    private LocalDateTime generatedDate;
    private String generatedBy; // User ID who generated the report
    private Map<String, Object> data; // Report data as key-value pairs
    private String status; // Generated, Processing, Failed
    
    public Report(String reportId, String reportType, String title, String description, String generatedBy) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.title = title;
        this.description = description;
        this.generatedBy = generatedBy;
        this.generatedDate = LocalDateTime.now();
        this.data = new HashMap<>();
        this.status = "Generated";
    }
    
    /**
     * Adds data to the report
     * @param key The data key
     * @param value The data value
     */
    public void addData(String key, Object value) {
        this.data.put(key, value);
    }
    
    /**
     * Gets data from the report
     * @param key The data key
     * @return The data value
     */
    public Object getData(String key) {
        return this.data.get(key);
    }
    
    /**
     * Gets formatted generation date
     * @return formatted date string
     */
    public String getFormattedGeneratedDate() {
        return generatedDate.toString();
    }
    
    /**
     * Checks if report is successfully generated
     * @return true if generated, false otherwise
     */
    public boolean isGenerated() {
        return "Generated".equals(status);
    }
    
    /**
     * Gets report summary
     * @return report summary string
     */
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Report: ").append(title).append("\n");
        summary.append("Type: ").append(reportType).append("\n");
        summary.append("Generated: ").append(getFormattedGeneratedDate()).append("\n");
        summary.append("Status: ").append(status).append("\n");
        summary.append("Data Points: ").append(data.size());
        return summary.toString();
    }
    
    // Getters and Setters
    
    public String getReportId() {
        return reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    public String getReportType() {
        return reportType;
    }
    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getGeneratedDate() {
        return generatedDate;
    }
    
    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }
    
    public String getGeneratedBy() {
        return generatedBy;
    }
    
    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }
    
    public Map<String, Object> getData() {
        return data;
    }
    
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", reportType='" + reportType + '\'' +
                ", title='" + title + '\'' +
                ", generatedDate=" + generatedDate +
                ", status='" + status + '\'' +
                '}';
    }
}

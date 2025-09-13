package eden.oldagehome.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsPanel extends JPanel {
    
    public ReportsPanel() {
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);
        
        // Reports content
        JPanel content = createReportsContent();
        add(content, BorderLayout.CENTER);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Reports");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        JLabel subtitleLabel = new JLabel("Generate and view system reports");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel headerText = new JPanel();
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.setBackground(new Color(245, 245, 245));
        headerText.add(titleLabel);
        headerText.add(subtitleLabel);
        
        JButton generateButton = new JButton("Generate Report");
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setBackground(new Color(70, 130, 180));
        generateButton.setForeground(Color.WHITE);
        generateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        generateButton.setBorderPainted(false);
        generateButton.setFocusPainted(false);
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateButton.addActionListener(e -> showGenerateReportDialog());
        
        header.add(headerText, BorderLayout.WEST);
        header.add(generateButton, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createReportsContent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(new Color(245, 245, 245));
        content.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Reports grid
        JPanel reportsGrid = new JPanel();
        reportsGrid.setLayout(new GridLayout(2, 3, 20, 20));
        reportsGrid.setBackground(new Color(245, 245, 245));
        
        // Create report cards
        reportsGrid.add(createReportCard("📊", "Occupancy Report", "View resident occupancy statistics and trends", "View Report"));
        reportsGrid.add(createReportCard("💊", "Medication Report", "Track medication usage and refill requirements", "View Report"));
        reportsGrid.add(createReportCard("👥", "Staff Report", "Monitor staff schedules and performance", "View Report"));
        reportsGrid.add(createReportCard("💰", "Financial Report", "Review budget and expense summaries", "View Report"));
        reportsGrid.add(createReportCard("📅", "Activities Report", "Analyze activity participation and engagement", "View Report"));
        reportsGrid.add(createReportCard("🏥", "Health Report", "Track resident health status and medical visits", "View Report"));
        
        content.add(reportsGrid, BorderLayout.CENTER);
        
        return content;
    }
    
    private JPanel createReportCard(String icon, String title, String description, String buttonText) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Top section with icon and title
        JPanel topSection = new JPanel();
        topSection.setLayout(new BorderLayout());
        topSection.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        topSection.add(iconLabel, BorderLayout.WEST);
        topSection.add(titleLabel, BorderLayout.CENTER);
        
        // Description
        JLabel descriptionLabel = new JLabel("<html><div style='width: 200px;'>" + description + "</div></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionLabel.setForeground(new Color(120, 120, 120));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        
        // Button
        JButton viewButton = new JButton(buttonText);
        viewButton.setFont(new Font("Arial", Font.PLAIN, 12));
        viewButton.setBackground(new Color(70, 130, 180));
        viewButton.setForeground(Color.WHITE);
        viewButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        viewButton.setBorderPainted(false);
        viewButton.setFocusPainted(false);
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewButton.addActionListener(e -> showReport(title));
        
        // Add hover effect
        viewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewButton.setBackground(new Color(60, 120, 170));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewButton.setBackground(new Color(70, 130, 180));
            }
        });
        
        JPanel centerSection = new JPanel();
        centerSection.setLayout(new BoxLayout(centerSection, BoxLayout.Y_AXIS));
        centerSection.setBackground(Color.WHITE);
        centerSection.add(descriptionLabel);
        centerSection.add(viewButton);
        
        card.add(topSection, BorderLayout.NORTH);
        card.add(centerSection, BorderLayout.CENTER);
        
        return card;
    }
    
    private void showReport(String reportType) {
        String message = "Generating " + reportType + "...\n\n";
        
        switch (reportType) {
            case "Occupancy Report":
                message += "• Total Residents: 128\n";
                message += "• Available Beds: 22\n";
                message += "• Occupancy Rate: 85.3%\n";
                message += "• New Admissions (This Month): 5\n";
                message += "• Discharges (This Month): 2";
                break;
            case "Medication Report":
                message += "• Total Medications: 45\n";
                message += "• Low Stock Items: 3\n";
                message += "• Refills Needed: 8\n";
                message += "• Monthly Cost: $2,450\n";
                message += "• Compliance Rate: 94.2%";
                break;
            case "Staff Report":
                message += "• Total Staff: 25\n";
                message += "• Active Today: 18\n";
                message += "• On Leave: 2\n";
                message += "• Overtime Hours: 12\n";
                message += "• Training Completed: 15";
                break;
            case "Financial Report":
                message += "• Monthly Revenue: $45,000\n";
                message += "• Monthly Expenses: $38,500\n";
                message += "• Net Profit: $6,500\n";
                message += "• Budget Utilization: 85.6%\n";
                message += "• Outstanding Payments: $2,100";
                break;
            case "Activities Report":
                message += "• Total Activities: 32\n";
                message += "• Average Participation: 18\n";
                message += "• Most Popular: Movie Night\n";
                message += "• Completion Rate: 96.8%\n";
                message += "• Resident Satisfaction: 4.7/5";
                break;
            case "Health Report":
                message += "• Total Residents: 128\n";
                message += "• Stable: 115\n";
                message += "• Needs Attention: 8\n";
                message += "• Doctor Visits (This Month): 45\n";
                message += "• Emergency Calls: 2";
                break;
        }
        
        JOptionPane.showMessageDialog(this, message, reportType, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showGenerateReportDialog() {
        String[] reportTypes = {
            "Occupancy Report",
            "Medication Report", 
            "Staff Report",
            "Financial Report",
            "Activities Report",
            "Health Report"
        };
        
        String selectedReport = (String) JOptionPane.showInputDialog(
            this,
            "Select a report type to generate:",
            "Generate Report",
            JOptionPane.QUESTION_MESSAGE,
            null,
            reportTypes,
            reportTypes[0]
        );
        
        if (selectedReport != null) {
            showReport(selectedReport);
        }
    }
}

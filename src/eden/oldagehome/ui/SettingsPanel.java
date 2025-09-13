package eden.oldagehome.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    
    public SettingsPanel() {
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);
        
        // Settings content
        JPanel content = createSettingsContent();
        add(content, BorderLayout.CENTER);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        JLabel subtitleLabel = new JLabel("Configure system settings and preferences");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel headerText = new JPanel();
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.setBackground(new Color(245, 245, 245));
        headerText.add(titleLabel);
        headerText.add(subtitleLabel);
        
        header.add(headerText, BorderLayout.WEST);
        
        return header;
    }
    
    private JPanel createSettingsContent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(new Color(245, 245, 245));
        content.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Settings grid
        JPanel settingsGrid = new JPanel();
        settingsGrid.setLayout(new GridLayout(2, 2, 20, 20));
        settingsGrid.setBackground(new Color(245, 245, 245));
        
        // Create settings cards
        settingsGrid.add(createSettingsCard("👤", "User Management", "Manage user accounts and permissions", "Manage Users"));
        settingsGrid.add(createSettingsCard("🏥", "Ward Configuration", "Configure wards and room assignments", "Configure Wards"));
        settingsGrid.add(createSettingsCard("🔔", "Notifications", "Set up alerts and notification preferences", "Configure Notifications"));
        settingsGrid.add(createSettingsCard("💾", "Data Backup", "Backup and restore system data", "Backup Data"));
        
        content.add(settingsGrid, BorderLayout.CENTER);
        
        return content;
    }
    
    private JPanel createSettingsCard(String icon, String title, String description, String buttonText) {
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
        JButton configButton = new JButton(buttonText);
        configButton.setFont(new Font("Arial", Font.PLAIN, 12));
        configButton.setBackground(new Color(70, 130, 180));
        configButton.setForeground(Color.WHITE);
        configButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        configButton.setBorderPainted(false);
        configButton.setFocusPainted(false);
        configButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        configButton.addActionListener(e -> showSettingsDialog(title));
        
        // Add hover effect
        configButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                configButton.setBackground(new Color(60, 120, 170));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                configButton.setBackground(new Color(70, 130, 180));
            }
        });
        
        JPanel centerSection = new JPanel();
        centerSection.setLayout(new BoxLayout(centerSection, BoxLayout.Y_AXIS));
        centerSection.setBackground(Color.WHITE);
        centerSection.add(descriptionLabel);
        centerSection.add(configButton);
        
        card.add(topSection, BorderLayout.NORTH);
        card.add(centerSection, BorderLayout.CENTER);
        
        return card;
    }
    
    private void showSettingsDialog(String settingType) {
        String message = "Configuration for " + settingType + ":\n\n";
        
        switch (settingType) {
            case "User Management":
                message += "• Total Users: 25\n";
                message += "• Active Users: 23\n";
                message += "• Admin Users: 3\n";
                message += "• Ward Employees: 20\n";
                message += "• Last Login: Today, 2:30 PM";
                break;
            case "Ward Configuration":
                message += "• Total Wards: 3\n";
                message += "• Garden View Ward: 45 beds\n";
                message += "• Sunset Wing: 40 beds\n";
                message += "• Memory Care Unit: 20 beds\n";
                message += "• Total Capacity: 105 beds";
                break;
            case "Notifications":
                message += "• Email Notifications: Enabled\n";
                message += "• SMS Alerts: Disabled\n";
                message += "• Medication Reminders: Enabled\n";
                message += "• Emergency Alerts: Enabled\n";
                message += "• Daily Reports: Enabled";
                break;
            case "Data Backup":
                message += "• Last Backup: Today, 6:00 AM\n";
                message += "• Backup Frequency: Daily\n";
                message += "• Backup Location: Local Server\n";
                message += "• Backup Size: 2.3 GB\n";
                message += "• Retention Period: 30 days";
                break;
        }
        
        JOptionPane.showMessageDialog(this, message, settingType + " Settings", JOptionPane.INFORMATION_MESSAGE);
    }
}

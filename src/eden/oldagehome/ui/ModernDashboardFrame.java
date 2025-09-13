package eden.oldagehome.ui;

import eden.oldagehome.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernDashboardFrame extends JFrame {
    
    private User currentUser;
    private JPanel mainContentPanel;
    private CardLayout cardLayout;
    
    public ModernDashboardFrame(User user) {
        this.currentUser = user;
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Eden Old Age Home - Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 700));
        
        // Set the look and feel to be more modern
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Main layout
        setLayout(new BorderLayout());
        
        // Create sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);
        
        // Create main content area
        mainContentPanel = new JPanel();
        cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.setBackground(new Color(245, 245, 245));
        
        // Add different panels
        mainContentPanel.add(createDashboardPanel(), "dashboard");
        mainContentPanel.add(createResidentsPanel(), "residents");
        mainContentPanel.add(createStaffPanel(), "staff");
        mainContentPanel.add(createActivitiesPanel(), "activities");
        mainContentPanel.add(createReportsPanel(), "reports");
        mainContentPanel.add(createSettingsPanel(), "settings");
        
        add(mainContentPanel, BorderLayout.CENTER);
        
        // Show dashboard by default
        cardLayout.show(mainContentPanel, "dashboard");
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));
        
        // Top section with logo
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBackground(Color.WHITE);
        topSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        
        // Logo
        JLabel logoLabel = new JLabel("🏠❤️");
        logoLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel appNameLabel = new JLabel("eden");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appNameLabel.setForeground(new Color(60, 60, 60));
        
        JLabel appSubLabel = new JLabel("OLD-AGE-HOME");
        appSubLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        appSubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appSubLabel.setForeground(new Color(120, 120, 120));
        
        topSection.add(logoLabel);
        topSection.add(appNameLabel);
        topSection.add(appSubLabel);
        
        // Navigation menu
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        // Navigation items
        String[] navItems = {"Dashboard", "Residents", "Staff", "Activities", "Reports", "Settings"};
        String[] navIcons = {"🏠", "👥", "👨‍⚕️", "📅", "📊", "⚙️"};
        String[] navCards = {"dashboard", "residents", "staff", "activities", "reports", "settings"};
        
        for (int i = 0; i < navItems.length; i++) {
            JButton navButton = createNavButton(navIcons[i], navItems[i], navCards[i]);
            navPanel.add(navButton);
            navPanel.add(Box.createVerticalStrut(5));
        }
        
        // User profile section at bottom
        JPanel userSection = new JPanel();
        userSection.setLayout(new BorderLayout());
        userSection.setBackground(Color.WHITE);
        userSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel userInfo = new JPanel();
        userInfo.setLayout(new BorderLayout());
        userInfo.setBackground(Color.WHITE);
        
        JLabel userAvatar = new JLabel("👤");
        userAvatar.setFont(new Font("Arial", Font.PLAIN, 24));
        userAvatar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JPanel userDetails = new JPanel();
        userDetails.setLayout(new BoxLayout(userDetails, BoxLayout.Y_AXIS));
        userDetails.setBackground(Color.WHITE);
        
        JLabel userNameLabel = new JLabel(currentUser.getName());
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        userNameLabel.setForeground(new Color(60, 60, 60));
        
        JLabel userRoleLabel = new JLabel(currentUser.getRole());
        userRoleLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        userRoleLabel.setForeground(new Color(120, 120, 120));
        
        userDetails.add(userNameLabel);
        userDetails.add(userRoleLabel);
        
        userInfo.add(userAvatar, BorderLayout.WEST);
        userInfo.add(userDetails, BorderLayout.CENTER);
        
        userSection.add(userInfo, BorderLayout.CENTER);
        
        sidebar.add(topSection, BorderLayout.NORTH);
        sidebar.add(navPanel, BorderLayout.CENTER);
        sidebar.add(userSection, BorderLayout.SOUTH);
        
        return sidebar;
    }
    
    private JButton createNavButton(String icon, String text, String cardName) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        textLabel.setForeground(new Color(80, 80, 80));
        
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);
        content.add(iconLabel, BorderLayout.WEST);
        content.add(textLabel, BorderLayout.CENTER);
        
        button.add(content);
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 248, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainContentPanel, cardName);
            }
        });
        
        return button;
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        JLabel greetingLabel = new JLabel("Good Morning, " + currentUser.getName());
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        greetingLabel.setForeground(new Color(60, 60, 60));
        
        JLabel subtitleLabel = new JLabel("Here's your community overview");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel headerText = new JPanel();
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.setBackground(new Color(245, 245, 245));
        headerText.add(greetingLabel);
        headerText.add(subtitleLabel);
        
        header.add(headerText, BorderLayout.WEST);
        
        // Dashboard cards
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(2, 3, 20, 20));
        cardsPanel.setBackground(new Color(245, 245, 245));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Create dashboard cards
        cardsPanel.add(createDashboardCard("Residents", "128", "Total Residents", "👥"));
        cardsPanel.add(createDashboardCard("Upcoming Activities", "45", "Movie Night 7 PM", "📅"));
        cardsPanel.add(createDashboardCard("Critical Alerts", "3", "Medication Reminders", "⚠️"));
        cardsPanel.add(createDashboardCard("Occupancy Trend", "85%", "Last 6 Months", "📈"));
        cardsPanel.add(createDashboardCard("Recent Updates", "5", "New Residents Added", "📝"));
        cardsPanel.add(createDashboardCard("Staff On Duty", "12", "Active Today", "👨‍⚕️"));
        
        panel.add(header, BorderLayout.NORTH);
        panel.add(cardsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createDashboardCard(String title, String value, String subtitle, String icon) {
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
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        topSection.add(iconLabel, BorderLayout.WEST);
        topSection.add(titleLabel, BorderLayout.CENTER);
        
        // Center section with value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setForeground(new Color(70, 130, 180));
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        // Bottom section with subtitle
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        
        JPanel centerSection = new JPanel();
        centerSection.setLayout(new BoxLayout(centerSection, BoxLayout.Y_AXIS));
        centerSection.setBackground(Color.WHITE);
        centerSection.add(valueLabel);
        centerSection.add(subtitleLabel);
        
        card.add(topSection, BorderLayout.NORTH);
        card.add(centerSection, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createResidentsPanel() {
        return new ResidentsManagementPanel();
    }
    
    private JPanel createStaffPanel() {
        return new StaffManagementPanel();
    }
    
    private JPanel createActivitiesPanel() {
        return new ActivitiesManagementPanel();
    }
    
    private JPanel createReportsPanel() {
        return new ReportsPanel();
    }
    
    private JPanel createSettingsPanel() {
        return new SettingsPanel();
    }
}

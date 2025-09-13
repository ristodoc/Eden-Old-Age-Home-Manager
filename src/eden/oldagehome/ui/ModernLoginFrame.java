package eden.oldagehome.ui;

import eden.oldagehome.repository.UserRepository;
import eden.oldagehome.repository.UserRepositoryImpl;
import eden.oldagehome.service.Authenticator;
import eden.oldagehome.util.DataInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernLoginFrame extends JFrame {
    
    private final Authenticator authenticator;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    
    public ModernLoginFrame() {
        UserRepository userRepository = new UserRepositoryImpl();
        this.authenticator = new Authenticator(userRepository);
        
        // Initialize sample data
        DataInitializer initializer = new DataInitializer();
        initializer.initializeSampleData();
        
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Eden Old Age Home - Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set the look and feel to be more modern
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Main panel with light gray background
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Center the login card
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        
        // Login card
        JPanel loginCard = createLoginCard();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(loginCard, gbc);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    private JPanel createLoginCard() {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        card.setPreferredSize(new Dimension(320, 400));
        
        // Add shadow effect
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        // Top section with icon and title
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBackground(Color.WHITE);
        topSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Home icon with heart
        JLabel iconLabel = new JLabel("🏠❤️");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Title
        JLabel titleLabel = new JLabel("Welcome Back");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(60, 60, 60));
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Login to manage your community");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 30, 0));
        
        topSection.add(iconLabel);
        topSection.add(titleLabel);
        topSection.add(subtitleLabel);
        
        // Form section
        JPanel formSection = new JPanel();
        formSection.setLayout(new BoxLayout(formSection, BoxLayout.Y_AXIS));
        formSection.setBackground(Color.WHITE);
        formSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Username field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameLabel.setForeground(new Color(80, 80, 80));
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        usernameField.setMaximumSize(new Dimension(280, 40));
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordLabel.setForeground(new Color(80, 80, 80));
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setMaximumSize(new Dimension(280, 40));
        passwordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        showPasswordCheckBox = new JCheckBox("👁️");
        showPasswordCheckBox.setBackground(Color.WHITE);
        showPasswordCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? (char) 0 : '•');
            }
        });
        
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(showPasswordCheckBox, BorderLayout.EAST);
        
        // Login button
        JButton loginButton = new JButton("LOG IN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        loginButton.setMaximumSize(new Dimension(280, 45));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> login());
        
        // Add hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(60, 120, 170));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(70, 130, 180));
            }
        });
        
        formSection.add(usernameLabel);
        formSection.add(usernameField);
        formSection.add(passwordLabel);
        formSection.add(passwordPanel);
        formSection.add(Box.createVerticalStrut(20));
        formSection.add(loginButton);
        
        // Bottom section with links
        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.Y_AXIS));
        bottomSection.setBackground(Color.WHITE);
        bottomSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomSection.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(new Color(120, 120, 120));
        forgotPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel signUpLabel = new JLabel("Need an account? Sign Up");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        signUpLabel.setForeground(new Color(70, 130, 180));
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        bottomSection.add(forgotPasswordLabel);
        bottomSection.add(signUpLabel);
        
        card.add(topSection, BorderLayout.NORTH);
        card.add(formSection, BorderLayout.CENTER);
        card.add(bottomSection, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", 
                "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Authenticator.AuthenticationResult result = authenticator.authenticate(username, password);
        
        if (result.isSuccess()) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + result.getRole(), 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Open the main application window based on the role
            switch (result.getRole()) {
                case "Admin":
                    new ModernDashboardFrame(result.getUser()).setVisible(true);
                    break;
                case "WardEmployee":
                    new ModernDashboardFrame(result.getUser()).setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Unknown role: " + result.getRole(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", 
                "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ModernLoginFrame().setVisible(true);
        });
    }
}

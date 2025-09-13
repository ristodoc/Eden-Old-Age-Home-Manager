package eden.oldagehome.ui;

import eden.oldagehome.repository.UserRepository;
import eden.oldagehome.repository.UserRepositoryImpl;
import eden.oldagehome.service.Authenticator;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final Authenticator authenticator;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        UserRepository userRepository = new UserRepositoryImpl();
        this.authenticator = new Authenticator(userRepository);

        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        panel.add(loginButton);

        add(panel);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        Authenticator.AuthenticationResult result = authenticator.authenticate(username, password);

        if (result.isSuccess()) {
            JOptionPane.showMessageDialog(this, "Login successful! Role: " + result.getRole());
            // Open the main application window based on the role
            switch (result.getRole()) {
                case "Admin":
                    new AdminPanel().setVisible(true);
                    break;
                case "WardEmployee":
                    new WardPanel().setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Unknown role: " + result.getRole(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ModernLoginFrame().setVisible(true);
        });
    }
}

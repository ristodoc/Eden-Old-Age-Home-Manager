package eden.oldagehome.ui;

import javax.swing.*;
import java.awt.*;

public class WardPanel extends JFrame {

    public WardPanel() {
        setTitle("Ward Employee Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Example content for the Ward Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Welcome to the Ward Employee Panel!", SwingConstants.CENTER), BorderLayout.CENTER);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WardPanel().setVisible(true);
        });
    }
}

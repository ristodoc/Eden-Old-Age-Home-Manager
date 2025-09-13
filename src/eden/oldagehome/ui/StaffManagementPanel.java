package eden.oldagehome.ui;

import eden.oldagehome.model.User;
import eden.oldagehome.repository.UserRepository;
import eden.oldagehome.repository.UserRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StaffManagementPanel extends JPanel {
    
    private UserRepository userRepository;
    private JTable staffTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    
    public StaffManagementPanel() {
        this.userRepository = new UserRepositoryImpl();
        initializeUI();
        loadStaff();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);
        
        // Control bar
        JPanel controlBar = createControlBar();
        add(controlBar, BorderLayout.CENTER);
        
        // Table
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Staff");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        JLabel subtitleLabel = new JLabel("Manage staff members and their roles");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel headerText = new JPanel();
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.setBackground(new Color(245, 245, 245));
        headerText.add(titleLabel);
        headerText.add(subtitleLabel);
        
        JButton addButton = new JButton("Add New Staff");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> showAddStaffDialog());
        
        header.add(headerText, BorderLayout.WEST);
        header.add(addButton, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createControlBar() {
        JPanel controlBar = new JPanel();
        controlBar.setLayout(new BorderLayout());
        controlBar.setBackground(new Color(245, 245, 245));
        controlBar.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        
        // Search section
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setBackground(new Color(245, 245, 245));
        
        JLabel filterLabel = new JLabel("Filter");
        filterLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        filterLabel.setForeground(new Color(80, 80, 80));
        filterLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        JPanel searchFieldPanel = new JPanel(new BorderLayout());
        searchFieldPanel.setBackground(Color.WHITE);
        searchFieldPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        searchFieldPanel.setMaximumSize(new Dimension(300, 40));
        
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        searchField.setOpaque(false);
        
        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setFont(new Font("Arial", Font.PLAIN, 14));
        searchIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
        
        searchFieldPanel.add(searchIcon, BorderLayout.WEST);
        searchFieldPanel.add(searchField, BorderLayout.CENTER);
        
        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(new BoxLayout(searchContainer, BoxLayout.Y_AXIS));
        searchContainer.setBackground(new Color(245, 245, 245));
        searchContainer.add(filterLabel);
        searchContainer.add(searchFieldPanel);
        
        controlBar.add(searchContainer, BorderLayout.WEST);
        
        return controlBar;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Table headers
        String[] columnNames = {"Name", "Role", "Email", "Status", "Actions"};
        
        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only Actions column is editable
            }
        };
        
        staffTable = new JTable(tableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setRowHeight(60);
        staffTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        staffTable.setShowGrid(false);
        staffTable.setIntercellSpacing(new Dimension(0, 0));
        
        // Customize table appearance
        staffTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        staffTable.getTableHeader().setBackground(new Color(248, 248, 248));
        staffTable.getTableHeader().setForeground(new Color(60, 60, 60));
        staffTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        
        // Custom cell renderer for better appearance
        staffTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    c.setBackground(new Color(240, 248, 255));
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                }
                
                c.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
                
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.setBackground(Color.WHITE);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private void loadStaff() {
        // For now, we'll create some sample staff data since we don't have a comprehensive staff repository
        updateTable();
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        
        // Sample staff data
        String[][] staffData = {
            {"Jane Smith", "Admin", "jane.smith@eden.com", "Active"},
            {"John Doe", "Ward Employee", "john.doe@eden.com", "Active"},
            {"Dr. Sarah Johnson", "Doctor", "sarah.johnson@eden.com", "Active"},
            {"Mike Wilson", "Nurse", "mike.wilson@eden.com", "Active"},
            {"Lisa Brown", "Caregiver", "lisa.brown@eden.com", "On Leave"},
            {"Dr. Robert Davis", "Doctor", "robert.davis@eden.com", "Active"},
            {"Emma Garcia", "Nurse", "emma.garcia@eden.com", "Active"},
            {"Tom Miller", "Maintenance", "tom.miller@eden.com", "Active"}
        };
        
        for (String[] staff : staffData) {
            Object[] row = {
                createNameCell(staff[0]),
                createRoleCell(staff[1]),
                createEmailCell(staff[2]),
                createStatusCell(staff[3]),
                createActionsCell(staff[0])
            };
            tableModel.addRow(row);
        }
    }
    
    private JPanel createNameCell(String name) {
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(Color.WHITE);
        
        JLabel avatarLabel = new JLabel("👤");
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        avatarLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(new Color(60, 60, 60));
        
        namePanel.add(avatarLabel, BorderLayout.WEST);
        namePanel.add(nameLabel, BorderLayout.CENTER);
        
        return namePanel;
    }
    
    private JLabel createRoleCell(String role) {
        Color roleColor = role.equals("Admin") ? new Color(70, 130, 180) : 
                         role.equals("Doctor") ? new Color(40, 167, 69) :
                         role.equals("Nurse") ? new Color(255, 193, 7) :
                         new Color(108, 117, 125);
        
        JLabel roleLabel = new JLabel(role);
        roleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        roleLabel.setForeground(roleColor);
        roleLabel.setBackground(new Color(248, 249, 250));
        roleLabel.setOpaque(true);
        roleLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        return roleLabel;
    }
    
    private JLabel createEmailCell(String email) {
        JLabel emailLabel = new JLabel(email);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailLabel.setForeground(new Color(80, 80, 80));
        
        return emailLabel;
    }
    
    private JLabel createStatusCell(String status) {
        Color statusColor = status.equals("Active") ? new Color(40, 167, 69) : new Color(255, 193, 7);
        Color bgColor = status.equals("Active") ? new Color(212, 237, 218) : new Color(255, 243, 205);
        
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setForeground(statusColor);
        statusLabel.setBackground(bgColor);
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        return statusLabel;
    }
    
    private JButton createActionsCell(String name) {
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        editButton.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        editButton.setBorderPainted(false);
        editButton.setFocusPainted(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(e -> showEditStaffDialog(name));
        
        return editButton;
    }
    
    private void showAddStaffDialog() {
        JOptionPane.showMessageDialog(this, "Add Staff dialog will be implemented here", "Add Staff", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showEditStaffDialog(String name) {
        JOptionPane.showMessageDialog(this, "Edit Staff dialog for: " + name, "Edit Staff", JOptionPane.INFORMATION_MESSAGE);
    }
}

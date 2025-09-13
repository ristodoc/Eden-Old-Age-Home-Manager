package eden.oldagehome.ui;

import eden.oldagehome.model.Resident;
import eden.oldagehome.repository.ResidentRepository;
import eden.oldagehome.repository.ResidentRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResidentsManagementPanel extends JPanel {
    
    private ResidentRepository residentRepository;
    private JTable residentsTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> sortComboBox;
    private List<Resident> currentResidents;
    
    public ResidentsManagementPanel() {
        this.residentRepository = new ResidentRepositoryImpl();
        initializeUI();
        loadResidents();
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
        
        JLabel titleLabel = new JLabel("Residents");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        JLabel subtitleLabel = new JLabel("Manage resident profiles and information");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel headerText = new JPanel();
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.setBackground(new Color(245, 245, 245));
        headerText.add(titleLabel);
        headerText.add(subtitleLabel);
        
        JButton addButton = new JButton("Add New Resident");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> showAddResidentDialog());
        
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
        
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterResidents(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterResidents(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterResidents(); }
        });
        
        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(new BoxLayout(searchContainer, BoxLayout.Y_AXIS));
        searchContainer.setBackground(new Color(245, 245, 245));
        searchContainer.add(filterLabel);
        searchContainer.add(searchFieldPanel);
        
        // Sort section
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new BorderLayout());
        sortPanel.setBackground(new Color(245, 245, 245));
        
        JButton sortButton = new JButton("Sort by: Last Name");
        sortButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortButton.setBackground(Color.WHITE);
        sortButton.setForeground(new Color(80, 80, 80));
        sortButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        sortButton.setFocusPainted(false);
        sortButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sortButton.addActionListener(e -> showSortOptions());
        
        sortPanel.add(sortButton, BorderLayout.CENTER);
        
        controlBar.add(searchContainer, BorderLayout.WEST);
        controlBar.add(sortPanel, BorderLayout.EAST);
        
        return controlBar;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        
        // Table headers
        String[] columnNames = {"Name", "Status", "Care Level", "Last Active", "Actions"};
        
        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only Actions column is editable
            }
        };
        
        residentsTable = new JTable(tableModel);
        residentsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        residentsTable.setRowHeight(60);
        residentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        residentsTable.setShowGrid(false);
        residentsTable.setIntercellSpacing(new Dimension(0, 0));
        
        // Customize table appearance
        residentsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        residentsTable.getTableHeader().setBackground(new Color(248, 248, 248));
        residentsTable.getTableHeader().setForeground(new Color(60, 60, 60));
        residentsTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        
        // Custom cell renderer for better appearance
        residentsTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
        
        JScrollPane scrollPane = new JScrollPane(residentsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.setBackground(Color.WHITE);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private void loadResidents() {
        currentResidents = residentRepository.findAll();
        updateTable();
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        
        for (Resident resident : currentResidents) {
            Object[] row = {
                createNameCell(resident),
                createStatusCell(resident),
                createCareLevelCell(resident),
                createLastActiveCell(resident),
                createActionsCell(resident)
            };
            tableModel.addRow(row);
        }
    }
    
    private JPanel createNameCell(Resident resident) {
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(Color.WHITE);
        
        JLabel avatarLabel = new JLabel("👤");
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        avatarLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JPanel nameInfo = new JPanel();
        nameInfo.setLayout(new BoxLayout(nameInfo, BoxLayout.Y_AXIS));
        nameInfo.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(resident.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(new Color(60, 60, 60));
        
        JLabel roomLabel = new JLabel("Room " + (resident.getWardId() != null ? resident.getWardId() : "N/A"));
        roomLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roomLabel.setForeground(new Color(120, 120, 120));
        
        nameInfo.add(nameLabel);
        nameInfo.add(roomLabel);
        
        namePanel.add(avatarLabel, BorderLayout.WEST);
        namePanel.add(nameInfo, BorderLayout.CENTER);
        
        return namePanel;
    }
    
    private JLabel createStatusCell(Resident resident) {
        String status = resident.needsRefill() ? "Needs Attention" : "Stable";
        Color statusColor = resident.needsRefill() ? new Color(220, 53, 69) : new Color(40, 167, 69);
        Color bgColor = resident.needsRefill() ? new Color(248, 215, 218) : new Color(212, 237, 218);
        
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setForeground(statusColor);
        statusLabel.setBackground(bgColor);
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        return statusLabel;
    }
    
    private JLabel createCareLevelCell(Resident resident) {
        String careLevel = determineCareLevel(resident);
        JLabel careLabel = new JLabel(careLevel);
        careLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        careLabel.setForeground(new Color(80, 80, 80));
        
        return careLabel;
    }
    
    private JLabel createLastActiveCell(Resident resident) {
        JLabel activeLabel = new JLabel("15 mins ago");
        activeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        activeLabel.setForeground(new Color(120, 120, 120));
        
        return activeLabel;
    }
    
    private JButton createActionsCell(Resident resident) {
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        editButton.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        editButton.setBorderPainted(false);
        editButton.setFocusPainted(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(e -> showEditResidentDialog(resident));
        
        return editButton;
    }
    
    private String determineCareLevel(Resident resident) {
        if (resident.getAge() >= 80) {
            return "Memory Care";
        } else if (resident.getAge() >= 70) {
            return "Assisted Living";
        } else {
            return "Independent";
        }
    }
    
    private void filterResidents() {
        String searchText = searchField.getText().toLowerCase();
        
        if (searchText.isEmpty()) {
            loadResidents();
        } else {
            currentResidents = residentRepository.searchByName(searchText);
            updateTable();
        }
    }
    
    private void showSortOptions() {
        // For now, just show a simple message
        JOptionPane.showMessageDialog(this, "Sort options will be implemented here", "Sort Options", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showAddResidentDialog() {
        JOptionPane.showMessageDialog(this, "Add Resident dialog will be implemented here", "Add Resident", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showEditResidentDialog(Resident resident) {
        JOptionPane.showMessageDialog(this, "Edit Resident dialog for: " + resident.getName(), "Edit Resident", JOptionPane.INFORMATION_MESSAGE);
    }
}

package eden.oldagehome.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ActivitiesManagementPanel extends JPanel {
    
    private JTable activitiesTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    
    public ActivitiesManagementPanel() {
        initializeUI();
        loadActivities();
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
        
        JLabel titleLabel = new JLabel("Activities");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        JLabel subtitleLabel = new JLabel("Manage scheduled activities and events");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel headerText = new JPanel();
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.setBackground(new Color(245, 245, 245));
        headerText.add(titleLabel);
        headerText.add(subtitleLabel);
        
        JButton addButton = new JButton("Add New Activity");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> showAddActivityDialog());
        
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
        String[] columnNames = {"Activity", "Date & Time", "Location", "Participants", "Status", "Actions"};
        
        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only Actions column is editable
            }
        };
        
        activitiesTable = new JTable(tableModel);
        activitiesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        activitiesTable.setRowHeight(60);
        activitiesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        activitiesTable.setShowGrid(false);
        activitiesTable.setIntercellSpacing(new Dimension(0, 0));
        
        // Customize table appearance
        activitiesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        activitiesTable.getTableHeader().setBackground(new Color(248, 248, 248));
        activitiesTable.getTableHeader().setForeground(new Color(60, 60, 60));
        activitiesTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        
        // Custom cell renderer for better appearance
        activitiesTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
        
        JScrollPane scrollPane = new JScrollPane(activitiesTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.setBackground(Color.WHITE);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private void loadActivities() {
        updateTable();
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        
        // Sample activities data
        String[][] activitiesData = {
            {"Movie Night", "Today, 7:00 PM", "Common Room", "25", "Scheduled"},
            {"Yoga Class", "Tomorrow, 10:00 AM", "Activity Hall", "15", "Scheduled"},
            {"Bingo Night", "Friday, 6:30 PM", "Common Room", "30", "Scheduled"},
            {"Art Therapy", "Monday, 2:00 PM", "Art Room", "12", "Scheduled"},
            {"Music Therapy", "Wednesday, 3:00 PM", "Music Room", "18", "Scheduled"},
            {"Gardening Club", "Thursday, 9:00 AM", "Garden", "8", "Scheduled"},
            {"Book Club", "Tuesday, 4:00 PM", "Library", "10", "Scheduled"},
            {"Exercise Class", "Monday, 11:00 AM", "Activity Hall", "20", "Scheduled"}
        };
        
        for (String[] activity : activitiesData) {
            Object[] row = {
                createActivityCell(activity[0]),
                createDateTimeCell(activity[1]),
                createLocationCell(activity[2]),
                createParticipantsCell(activity[3]),
                createStatusCell(activity[4]),
                createActionsCell(activity[0])
            };
            tableModel.addRow(row);
        }
    }
    
    private JPanel createActivityCell(String activityName) {
        JPanel activityPanel = new JPanel(new BorderLayout());
        activityPanel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel("🎬");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JLabel nameLabel = new JLabel(activityName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(new Color(60, 60, 60));
        
        activityPanel.add(iconLabel, BorderLayout.WEST);
        activityPanel.add(nameLabel, BorderLayout.CENTER);
        
        return activityPanel;
    }
    
    private JLabel createDateTimeCell(String dateTime) {
        JLabel dateTimeLabel = new JLabel(dateTime);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateTimeLabel.setForeground(new Color(80, 80, 80));
        
        return dateTimeLabel;
    }
    
    private JLabel createLocationCell(String location) {
        JLabel locationLabel = new JLabel(location);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        locationLabel.setForeground(new Color(80, 80, 80));
        
        return locationLabel;
    }
    
    private JLabel createParticipantsCell(String participants) {
        JLabel participantsLabel = new JLabel(participants + " residents");
        participantsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        participantsLabel.setForeground(new Color(80, 80, 80));
        
        return participantsLabel;
    }
    
    private JLabel createStatusCell(String status) {
        Color statusColor = status.equals("Scheduled") ? new Color(40, 167, 69) : 
                           status.equals("Completed") ? new Color(108, 117, 125) :
                           new Color(255, 193, 7);
        Color bgColor = status.equals("Scheduled") ? new Color(212, 237, 218) : 
                       status.equals("Completed") ? new Color(233, 236, 239) :
                       new Color(255, 243, 205);
        
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setForeground(statusColor);
        statusLabel.setBackground(bgColor);
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        return statusLabel;
    }
    
    private JButton createActionsCell(String activityName) {
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        editButton.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        editButton.setBorderPainted(false);
        editButton.setFocusPainted(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(e -> showEditActivityDialog(activityName));
        
        return editButton;
    }
    
    private void showAddActivityDialog() {
        JOptionPane.showMessageDialog(this, "Add Activity dialog will be implemented here", "Add Activity", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showEditActivityDialog(String activityName) {
        JOptionPane.showMessageDialog(this, "Edit Activity dialog for: " + activityName, "Edit Activity", JOptionPane.INFORMATION_MESSAGE);
    }
}

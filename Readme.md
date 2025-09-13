# Eden Old Age Home Management System

A comprehensive Java-based management system for old age homes, designed to streamline operations and improve care quality for residents.

## Features

### 🏠 Dashboard
- **Overview Cards**: Real-time statistics for residents, activities, alerts, and occupancy trends
- **Personalized Greeting**: Welcome message with user-specific information
- **Quick Access**: Easy navigation to all major system functions

### 👥 Resident Management
- **Resident Profiles**: Complete resident information including medical history, medications, and care levels
- **Search & Filter**: Advanced search capabilities to quickly find residents
- **Status Tracking**: Real-time status updates (Stable, Needs Attention, etc.)
- **Medication Management**: Track medication schedules and refill requirements
- **Care Level Classification**: Automatic categorization (Independent, Assisted Living, Memory Care)

### 👨‍⚕️ Staff Management
- **Staff Directory**: Complete staff information with roles and departments
- **Role-based Access**: Different access levels for Admin, Doctors, Nurses, and Caregivers
- **Schedule Management**: Track staff schedules and availability
- **Performance Monitoring**: Staff activity and performance tracking

### 📅 Activities Management
- **Event Scheduling**: Plan and manage resident activities and events
- **Participation Tracking**: Monitor resident engagement in activities
- **Resource Management**: Track locations and equipment for activities
- **Status Updates**: Real-time activity status (Scheduled, Completed, Cancelled)

### 📊 Reporting System
- **Occupancy Reports**: Track resident capacity and trends
- **Medication Reports**: Monitor medication usage and compliance
- **Staff Reports**: Analyze staff performance and schedules
- **Financial Reports**: Budget tracking and expense management
- **Health Reports**: Resident health status and medical visit tracking
- **Activities Reports**: Participation and engagement analytics

### ⚙️ Settings & Configuration
- **User Management**: Create and manage user accounts with role-based permissions
- **Ward Configuration**: Set up and manage different wards and room assignments
- **Notification Settings**: Configure alerts and notification preferences
- **Data Backup**: Automated backup and restore functionality

## Technology Stack

- **Language**: Java
- **UI Framework**: Java Swing
- **Database**: MySQL
- **Architecture**: MVC (Model-View-Controller) pattern
- **Design Pattern**: Repository pattern for data access

## Project Structure

```
src/eden/oldagehome/
├── model/              # Data models
│   ├── User.java
│   ├── Admin.java
│   ├── WardEmployee.java
│   ├── Resident.java
│   ├── Staff.java
│   ├── Activity.java
│   ├── Report.java
│   ├── Medication.java
│   ├── Doctor.java
│   ├── Ward.java
│   └── ...
├── repository/         # Data access layer
│   ├── UserRepository.java
│   ├── UserRepositoryImpl.java
│   ├── ResidentRepository.java
│   └── ResidentRepositoryImpl.java
├── service/           # Business logic
│   └── Authenticator.java
├── ui/                # User interface
│   ├── ModernLoginFrame.java
│   ├── ModernDashboardFrame.java
│   ├── ResidentsManagementPanel.java
│   ├── StaffManagementPanel.java
│   ├── ActivitiesManagementPanel.java
│   ├── ReportsPanel.java
│   └── SettingsPanel.java
└── util/              # Utilities
    ├── DBConnection.java
    ├── PasswordUtils.java
    └── DataInitializer.java
```

## Installation & Setup

### Prerequisites
- Java 8 or higher
- MySQL 5.7 or higher
- MySQL Connector/J

### Database Setup
1. Create a MySQL database named `eden_oldagehome`
2. Run the SQL script from `resources/db/schema.sql` to create tables
3. Update database connection settings in `DBConnection.java` if needed

### Running the Application
1. Clone the repository
2. Compile the Java files:
   ```bash
   javac -cp "lib/mysql-connector-j-9.4.0.jar" src/eden/oldagehome/**/*.java
   ```
3. Run the application:
   ```bash
   java -cp "src:lib/mysql-connector-j-9.4.0.jar" eden.oldagehome.ui.ModernLoginFrame
   ```

### Default Login Credentials
- **Admin**: 
  - Username: `admin001`
  - Password: `admin123`
- **Ward Employee**: 
  - Username: `emp001`
  - Password: `employee123`

## Design Guidelines

The application follows a modern, minimalistic design approach:

### Color Scheme
- **Primary**: Soft blues (#4682B4)
- **Background**: Light grays (#F5F5F5)
- **Text**: Dark grays (#3C3C3C)
- **Accents**: White and subtle shadows

### UI Components
- **Cards**: Rounded corners with subtle drop shadows
- **Buttons**: Clean, modern styling with hover effects
- **Tables**: Alternating row colors for better readability
- **Navigation**: Sidebar with clear icons and labels

### Typography
- **Headers**: Bold, larger fonts for emphasis
- **Body Text**: Clean, readable sans-serif fonts
- **Labels**: Consistent sizing and spacing

## Key Features Implementation

### Authentication System
- Secure password hashing using `PasswordUtils`
- Role-based access control
- Session management

### Data Management
- Repository pattern for clean data access
- Connection pooling for database efficiency
- Transaction management for data integrity

### User Interface
- Modern, responsive design
- Intuitive navigation
- Real-time data updates
- Search and filter capabilities

## Future Enhancements

- [ ] Mobile-responsive web interface
- [ ] Real-time notifications
- [ ] Advanced reporting with charts and graphs
- [ ] Integration with medical devices
- [ ] Multi-language support
- [ ] Advanced security features
- [ ] API for third-party integrations

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please contact the development team or create an issue in the repository.

---

**Eden Old Age Home Management System** - Caring for our community with technology.
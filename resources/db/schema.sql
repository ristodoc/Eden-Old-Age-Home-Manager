CREATE DATABASE IF NOT EXISTS old_age_home_db;
USE old_age_home_db;

-- Users table (for all types of users)
CREATE TABLE users (
    user_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_type ENUM('ADMIN', 'WARD_EMPLOYEE') NOT NULL
);

-- Admin table (extends users)
CREATE TABLE admin (
    admin_id VARCHAR(20) PRIMARY KEY,
    total_budget DECIMAL(15, 2) DEFAULT 0.00,
    FOREIGN KEY (admin_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Wards table
CREATE TABLE wards (
    ward_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    admin_id VARCHAR(20),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id) ON DELETE SET NULL
);

-- Residents table
CREATE TABLE residents (
    resident_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    disease VARCHAR(200),
    diet VARCHAR(200),
    ward_id VARCHAR(20),
    FOREIGN KEY (ward_id) REFERENCES wards(ward_id) ON DELETE SET NULL
);

-- Medications table
CREATE TABLE medications (
    medication_id INT AUTO_INCREMENT PRIMARY KEY,
    resident_id VARCHAR(20),
    name VARCHAR(100) NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    quantity_left INT NOT NULL,
    monthly_requirement INT NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE
);

-- Doctors table
CREATE TABLE doctors (
    doctor_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

-- Doctor visits table
CREATE TABLE doctor_visits (
    visit_id INT AUTO_INCREMENT PRIMARY KEY,
    resident_id VARCHAR(20),
    doctor_id VARCHAR(20),
    visit_date DATE NOT NULL,
    notes TEXT,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

-- Prescriptions table (for doctor visits)
CREATE TABLE prescriptions (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_id INT,
    medication_name VARCHAR(100) NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    duration INT NOT NULL, -- in days
    FOREIGN KEY (visit_id) REFERENCES doctor_visits(visit_id) ON DELETE CASCADE
);

-- Inventory items table
CREATE TABLE inventory_items (
    item_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity_left INT NOT NULL,
    monthly_requirement INT NOT NULL
);

-- Login entries table
CREATE TABLE login_entries (
    entry_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20),
    login_time DATETIME NOT NULL,
    logout_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Ward employees assignment table
CREATE TABLE ward_employees (
    employee_id VARCHAR(20),
    ward_id VARCHAR(20),
    PRIMARY KEY (employee_id, ward_id),
    FOREIGN KEY (employee_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (ward_id) REFERENCES wards(ward_id) ON DELETE CASCADE
);

-- Insert sample data
INSERT INTO users (user_id, name, email, password, user_type) VALUES
('ADMIN001', 'System Administrator', 'admin@example.com', 'admin123', 'ADMIN'),
('EMP001', 'John Doe', 'emp1@example.com', 'emp123', 'WARD_EMPLOYEE'),
('EMP002', 'Jane Smith', 'emp2@example.com', 'emp456', 'WARD_EMPLOYEE');

INSERT INTO admin (admin_id, total_budget) VALUES ('ADMIN001', 10000.00);

INSERT INTO wards (ward_id, name, admin_id) VALUES
('WARD001', 'General Care', 'ADMIN001'),
('WARD002', 'Special Care', 'ADMIN001');

INSERT INTO ward_employees (employee_id, ward_id) VALUES
('EMP001', 'WARD001'),
('EMP002', 'WARD002');

INSERT INTO residents (resident_id, name, age, disease, diet, ward_id) VALUES
('RES001', 'Robert Brown', 75, 'Arthritis', 'Low Sodium', 'WARD001'),
('RES002', 'Mary Johnson', 82, 'Diabetes', 'Diabetic', 'WARD001'),
('RES003', 'William Davis', 78, 'Hypertension', 'Low Fat', 'WARD002');

INSERT INTO medications (resident_id, name, dosage, quantity_left, monthly_requirement) VALUES
('RES001', 'Metformin', '500mg twice daily', 15, 60),
('RES001', 'Lisinopril', '10mg daily', 5, 30),
('RES002', 'Insulin', '20 units daily', 25, 30),
('RES003', 'Amlodipine', '5mg daily', 2, 30);

INSERT INTO doctors (doctor_id, name, specialization) VALUES
('DOC001', 'Dr. Sarah Wilson', 'Geriatrics'),
('DOC002', 'Dr. Michael Chen', 'Endocrinology');

INSERT INTO doctor_visits (resident_id, doctor_id, visit_date, notes) VALUES
('RES001', 'DOC001', '2023-10-15', 'Routine checkup, patient is stable'),
('RES002', 'DOC002', '2023-10-16', 'Adjusted insulin dosage');

INSERT INTO prescriptions (visit_id, medication_name, dosage, duration) VALUES
(1, 'Metformin', '500mg twice daily', 30),
(1, 'Lisinopril', '10mg daily', 30),
(2, 'Insulin', '25 units daily', 30);

INSERT INTO inventory_items (item_id, name, quantity_left, monthly_requirement) VALUES
('INV001', 'Bandages', 150, 200),
('INV002', 'Syringes', 80, 100),
('INV003', 'Gloves', 500, 600);
CREATE DATABASE old_age_home_db;
USE old_age_home_db;

-- Users table (for all types of users)
CREATE TABLE users (
    user_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_type ENUM('ADMIN', 'WARD_EMPLOYEE') NOT NULL
);

-- Wards table
CREATE TABLE wards (
    ward_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    admin_id VARCHAR(20),
    FOREIGN KEY (admin_id) REFERENCES users(user_id)
);

-- Residents table
CREATE TABLE residents (
    resident_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    disease VARCHAR(200),
    diet VARCHAR(200),
    ward_id VARCHAR(20),
    FOREIGN KEY (ward_id) REFERENCES wards(ward_id)
);

-- Medications table
CREATE TABLE medications (
    medication_id INT AUTO_INCREMENT PRIMARY KEY,
    resident_id VARCHAR(20),
    name VARCHAR(100) NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    quantity_left INT NOT NULL,
    monthly_requirement INT NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id)
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
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);

-- Prescriptions table (for doctor visits)
CREATE TABLE prescriptions (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_id INT,
    prescription_text VARCHAR(200) NOT NULL,
    FOREIGN KEY (visit_id) REFERENCES doctor_visits(visit_id)
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
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Ward employees assignment table
CREATE TABLE ward_employees (
    employee_id VARCHAR(20),
    ward_id VARCHAR(20),
    PRIMARY KEY (employee_id, ward_id),
    FOREIGN KEY (employee_id) REFERENCES users(user_id),
    FOREIGN KEY (ward_id) REFERENCES wards(ward_id)
);
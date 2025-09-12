CREATE DATABASE old_age_home CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';

-- USERS (Admin, WardEmployee)
CREATE TABLE users (
    user_id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'WardEmployee') NOT NULL
) ENGINE=InnoDB;

-- WARDS
CREATE TABLE wards (
    ward_id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(150) NOT NULL
) ENGINE=InnoDB;

-- RESIDENTS
CREATE TABLE residents (
    resident_id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    ward_id VARCHAR(36),
    FOREIGN KEY (ward_id) REFERENCES wards(ward_id) ON DELETE SET NULL
) ENGINE=InnoDB;


-- MEDICATIONS (per resident)
CREATE TABLE medications (
medication_id VARCHAR(36) PRIMARY KEY,
resident_id VARCHAR(36) NOT NULL,
name VARCHAR(150) NOT NULL,
dosage VARCHAR(50),
quantity_left INT DEFAULT 0,
monthly_requirement INT DEFAULT 0,
last_refill_date DATE,
FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE
) ENGINE=InnoDB;


-- DOCTORS
CREATE TABLE doctors (
    doctor_id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    specialization VARCHAR(150)
) ENGINE=InnoDB;


-- DOCTOR VISITS
CREATE TABLE doctor_visits (
visit_id VARCHAR(36) PRIMARY KEY,
resident_id VARCHAR(36) NOT NULL,
doctor_id VARCHAR(36),
visit_date DATE NOT NULL,
notes TEXT,
prescriptions JSON,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE,
FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE SET NULL
) ENGINE=InnoDB;


-- INVENTORY
CREATE TABLE inventory_items (
item_id VARCHAR(36) PRIMARY KEY,
name VARCHAR(200) NOT NULL,
quantity_left INT DEFAULT 0,
monthly_requirement INT DEFAULT 0,
last_restock DATE,
notes TEXT
) ENGINE=InnoDB;


-- LOGIN LOGS
CREATE TABLE login_logs (
log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
user_id VARCHAR(36) NOT NULL,
login_time DATETIME,
logout_time DATETIME,
ip_address VARCHAR(45),
FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;



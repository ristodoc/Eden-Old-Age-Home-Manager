-- Drop tables if they exist to start with a clean slate
DROP TABLE IF EXISTS `login_history`;
DROP TABLE IF EXISTS `prescriptions`;
DROP TABLE IF EXISTS `doctor_visits`;
DROP TABLE IF EXISTS `medications`;
DROP TABLE IF EXISTS `inventory`;
DROP TABLE IF EXISTS `residents`;
DROP TABLE IF EXISTS `ward_employees`;
DROP TABLE IF EXISTS `wards`;
DROP TABLE IF EXISTS `doctors`;
DROP TABLE IF EXISTS `users`;

-- users table to store all user types (Admin, WardEmployee)
CREATE TABLE `users` (
  `user_id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('admin','ward_employee') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
);

-- doctors table
CREATE TABLE `doctors` (
  `doctor_id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `specialization` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`)
);

-- wards table
CREATE TABLE `wards` (
  `ward_id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`ward_id`)
);

-- ward_employees table, linked to users and wards
CREATE TABLE `ward_employees` (
  `employee_id` varchar(50) NOT NULL,
  `ward_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `ward_id` (`ward_id`),
  CONSTRAINT `ward_employees_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `ward_employees_ibfk_2` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`ward_id`) ON DELETE SET NULL
);

-- residents table
CREATE TABLE `residents` (
  `resident_id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `age` int DEFAULT NULL,
  `disease` varchar(255) DEFAULT NULL,
  `diet` varchar(255) DEFAULT NULL,
  `ward_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`resident_id`),
  KEY `ward_id` (`ward_id`),
  CONSTRAINT `residents_ibfk_1` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`ward_id`) ON DELETE SET NULL
);

-- inventory table
CREATE TABLE `inventory` (
  `item_id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quantity_left` int DEFAULT '0',
  `monthly_requirement` int DEFAULT '0',
  PRIMARY KEY (`item_id`)
);

-- medications table, linked to residents
CREATE TABLE `medications` (
  `medication_id` int NOT NULL AUTO_INCREMENT,
  `resident_id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `dosage` varchar(50) DEFAULT NULL,
  `quantity_left` int DEFAULT '0',
  `monthly_requirement` int DEFAULT '0',
  PRIMARY KEY (`medication_id`),
  KEY `resident_id` (`resident_id`),
  CONSTRAINT `medications_ibfk_1` FOREIGN KEY (`resident_id`) REFERENCES `residents` (`resident_id`) ON DELETE CASCADE
);

-- doctor_visits table
CREATE TABLE `doctor_visits` (
  `visit_id` int NOT NULL AUTO_INCREMENT,
  `resident_id` varchar(50) NOT NULL,
  `doctor_id` varchar(50) NOT NULL,
  `visit_date` date NOT NULL,
  `notes` text,
  PRIMARY KEY (`visit_id`),
  KEY `resident_id` (`resident_id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `doctor_visits_ibfk_1` FOREIGN KEY (`resident_id`) REFERENCES `residents` (`resident_id`) ON DELETE CASCADE,
  CONSTRAINT `doctor_visits_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`) ON DELETE CASCADE
);

-- prescriptions table for doctor visits
CREATE TABLE `prescriptions` (
  `prescription_id` int NOT NULL AUTO_INCREMENT,
  `visit_id` int NOT NULL,
  `prescription` varchar(255) NOT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `visit_id` (`visit_id`),
  CONSTRAINT `prescriptions_ibfk_1` FOREIGN KEY (`visit_id`) REFERENCES `doctor_visits` (`visit_id`) ON DELETE CASCADE
);

-- login_history table
CREATE TABLE `login_history` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `login_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
);
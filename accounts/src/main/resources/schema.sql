CREATE TABLE IF NOT EXISTS accounts (
    user_id BINARY(16) PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(11) NOT NULL,
    password VARCHAR(100),
    role ENUM('PATIENT', 'RECEPTIONIST', 'PHARMACIST', 'DOCTOR', 'ADMIN') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);
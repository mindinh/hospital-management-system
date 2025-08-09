CREATE TABLE IF NOT EXISTS patients (
    account_id BINARY(16),
    patient_id VARCHAR(20),
    patient_mobile_number VARCHAR(10),
    patient_fullname VARCHAR(30),
    patient_dob DATE,
    patient_gender ENUM('MALE', 'FEMALE'),
    patient_address VARCHAR(50),
    medical_insurance_number VARCHAR(50),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT PK_PATIENTS PRIMARY KEY (patient_id)
);


CREATE TABLE IF NOT EXISTS patient_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id VARCHAR(20) NOT NULL,
    visit_date DATETIME NOT NULL,
    visit_reasons TEXT,
    symptoms TEXT,
    diagnosis TEXT,
    treament_notes TEXT,
    doctor_id VARCHAR(20) NOT NULL,
    deparment VARCHAR(30) NOT NULL,


    CONSTRAINT FK_RECORDS_PATIENTS FOREIGN KEY (patient_id)
    REFERENCES patients (patient_id)
);



CREATE TABLE IF NOT EXISTS patients (
    account_id BINARY(16),
    patient_number VARCHAR(20),
    patient_mobile_number VARCHAR(10),
    patient_fullname VARCHAR(30),
    patient_dob DATE NOT NULL,
    patient_gender ENUM('MALE', 'FEMALE') NOT NULL,
    patient_address VARCHAR(50) NOT NULL,
    mdeical_insurance_number VARCHAR(50),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    PRIMARY KEY (account_id, patient_number);
);


CREATE TABLE IF NOT EXISTS patient_records (
    patient_id VARCHAR(20),
    visit_date DATETIME,
    symptoms VARCHAR(100) NOT NULL,
    diagnosis VARCHAR(100) NOT NULL,
    doctor_id VARCHAR(20),
    deparment VARCHAR(30),
    treament_notes VARCHAR(100),

    PRIMARY KEY (patient_id, visit_date)
);



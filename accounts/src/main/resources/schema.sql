DROP TABLE IF EXISTS taikhoan;

CREATE TABLE taikhoan (
    ma_tai_khoan VARCHAR(16) PRIMARY KEY,
    email VARCHAR(100),
    ten_nguoi_dung VARCHAR(100),
    so_dt CHAR(10),
    mat_khau VARCHAR(100),
    role ENUM('BENHNHAN', 'TIEPTAN', 'DUOCSI', 'BACSI', 'ADMIN') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

INSERT INTO taikhoan (ma_tai_khoan,email,ten_nguoi_dung,so_dt,mat_khau,role,status,created_at,created_by,updated_at,updated_by) VALUES
     ('AD-2508113ERDKQ9','ngvana@gmail.com','ngvana','0123456789','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','ADMIN','ACTIVE','2025-08-11 13:40:54','account-service',NULL,NULL),
     ('BN-250816Y63OUCH','tranvanbn@gmail.com','tranvanbn','0923456789','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-16 18:50:24','account-service',NULL,NULL),
     ('BN-250818QESRHVJ','levanbn@gmail.com','levanbn','0908345678','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-18 21:56:01','account-service',NULL,NULL),
     ('BS-2508114ROHDGI','ngvanbs@gmail.com','ngvanbs','0913456789','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BACSI','ACTIVE','2025-08-11 14:15:54','account-service',NULL,NULL),
     ('DS-250811IEG9XJF','ngvands@gmail.com','ngvands','0223456789','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','DUOCSI','ACTIVE','2025-08-11 14:15:18','account-service',NULL,NULL),
     ('TT-250811FY2N8G1','ngvantt@gmail.com','ngvantt','0213456789','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','TIEPTAN','ACTIVE','2025-08-11 14:15:33','account-service',NULL,NULL);

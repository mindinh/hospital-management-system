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
     ('TT-250811FY2N8G1','ngvantt@gmail.com','ngvantt','0213456789','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','TIEPTAN','ACTIVE','2025-08-11 14:15:33','account-service',NULL,NULL),
     ('BN-250819ABC123X','bn001@gmail.com','bn001','0911111111','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-19 09:15:24','account-service',NULL,NULL),
     ('BN-250819XYZ789K','bn002@gmail.com','bn002','0911111112','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-19 10:20:10','account-service',NULL,NULL),
     ('BN-250820LMN456Q','bn003@gmail.com','bn003','0911111113','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-20 08:05:42','account-service',NULL,NULL),
     ('BN-250820PQR852Z','bn004@gmail.com','bn004','0911111114','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-20 12:40:33','account-service',NULL,NULL),
     ('BN-250820JKL963T','bn005@gmail.com','bn005','0911111115','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-20 15:22:55','account-service',NULL,NULL),
     ('BN-250821GHI741M','bn006@gmail.com','bn006','0911111116','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-21 09:13:18','account-service',NULL,NULL),
     ('BN-250821UVW258B','bn007@gmail.com','bn007','0911111117','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-21 11:47:29','account-service',NULL,NULL),
     ('BN-250821DEF369L','bn008@gmail.com','bn008','0911111118','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-21 16:22:40','account-service',NULL,NULL),
     ('BN-250822QAZ159W','bn009@gmail.com','bn009','0911111119','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-22 08:05:12','account-service',NULL,NULL),
     ('BN-250822WSX753E','bn010@gmail.com','bn010','0911111120','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-22 09:45:44','account-service',NULL,NULL),
     ('BN-250822EDC456R','bn011@gmail.com','bn011','0911111121','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-22 11:33:17','account-service',NULL,NULL),
     ('BN-250822RFV852N','bn012@gmail.com','bn012','0911111122','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-22 14:26:58','account-service',NULL,NULL),
     ('BN-250822TGB147Y','bn013@gmail.com','bn013','0911111123','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-22 18:44:21','account-service',NULL,NULL),
     ('BN-250823YHN951C','bn014@gmail.com','bn014','0911111124','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 07:50:32','account-service',NULL,NULL),
     ('BN-250823UJM357O','bn015@gmail.com','bn015','0911111125','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 09:13:08','account-service',NULL,NULL),
     ('BN-250823IKM258P','bn016@gmail.com','bn016','0911111126','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 11:45:19','account-service',NULL,NULL),
     ('BN-250823OLP654Q','bn017@gmail.com','bn017','0911111127','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 13:22:41','account-service',NULL,NULL),
     ('BN-250823QWE963S','bn018@gmail.com','bn018','0911111128','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 15:30:25','account-service',NULL,NULL),
     ('BN-250823RTY741D','bn019@gmail.com','bn019','0911111129','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 17:45:11','account-service',NULL,NULL),
     ('BN-250823FGH258A','bn020@gmail.com','bn020','0911111130','$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6','BENHNHAN','ACTIVE','2025-08-23 19:05:54','account-service',NULL,NULL);

INSERT INTO taikhoan (ma_tai_khoan, email, ten_nguoi_dung, so_dt, mat_khau, role, status, created_at, created_by)
VALUES
-- Bác sĩ
('BS-250824ABC123X', 'bacsi01@hospital.com', 'Nguyen Van Bac', '0911111111', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'BACSI', 'ACTIVE', NOW(), 'accounts-service'),
('BS-250824JKL789M', 'bacsi02@hospital.com', 'Pham Thi Hoa', '0944444444', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'BACSI', 'ACTIVE', NOW(), 'accounts-service'),
('BS-250824XYZ456Q', 'bacsi03@hospital.com', 'Le Van An', '0977777777', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'BACSI', 'ACTIVE', NOW(), 'accounts-service'),

-- Dược sĩ
('DS-250824QWE789Z', 'duocsi01@hospital.com', 'Tran Thi Duoc', '0922222222', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'DUOCSI', 'ACTIVE', NOW(), 'accounts-service'),
('DS-250824RTY123P', 'duocsi02@hospital.com', 'Nguyen Van Thuoc', '0955555555', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'DUOCSI', 'ACTIVE', NOW(), 'accounts-service'),

-- Tiếp tân
('TT-250824LMN456K', 'tieptan01@hospital.com', 'Le Van Tan', '0933333333', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'TIEPTAN', 'ACTIVE', NOW(), 'accounts-service'),
('TT-250824POI852V', 'tieptan02@hospital.com', 'Pham Thi Lan', '0966666666', '$2a$10$exeKn1rNZbuvdErAy4VWAOjcvzKrwXKaf4pt/JeFd.b48zFh7Qje6', 'TIEPTAN', 'ACTIVE', NOW(), 'accounts-service');

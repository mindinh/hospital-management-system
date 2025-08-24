DROP TABLE IF EXISTS lichlamviec;
DROP TABLE IF EXISTS bangcap;
DROP TABLE IF EXISTS nhanvien;
DROP TABLE IF EXISTS khoa;


CREATE TABLE khoa (
    ma_khoa INT PRIMARY KEY AUTO_INCREMENT,
    ten_khoa VARCHAR(50),
    gioi_thieu_khoa TEXT,
    truong_khoa VARCHAR(20) DEFAULT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE nhanvien (
    ma_nv VARCHAR(16) PRIMARY KEY,
    ma_chung_chi VARCHAR(15),
    so_dt_nv CHAR(10),
    ho_ten_nv VARCHAR(30),
    ngay_sinh_nv DATE,
    gioi_tinh_nv ENUM('NAM', 'NU'),
    dia_chi_nv VARCHAR(100),
    chuc_vu ENUM('BACSI', 'TIEPTAN', 'DUOCSI', 'ADMIN'),
    kinh_nghiem TEXT,
    ma_khoa INT DEFAULT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT FK_NHANVIEN_KHOA FOREIGN KEY (ma_khoa)
    REFERENCES khoa (ma_khoa)
);

CREATE TABLE bangcap (
    ma_bang_cap INT PRIMARY KEY AUTO_INCREMENT,
    ma_nv VARCHAR(16),
    ten_bang_cap VARCHAR(30),
    ngay_cap DATE,
    don_vi_cap VARCHAR(30),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT FK_BANGCAP_NHANVIEN FOREIGN KEY (ma_nv)
    REFERENCES nhanvien (ma_nv)
);

CREATE TABLE lichlamviec (
    ma_lich INT PRIMARY KEY AUTO_INCREMENT,
    ma_bac_si VARCHAR(16) NOT NULL,
    ngay_lam_viec DATE NOT NULL,
    gio_bat_dau TIME NOT NULL,
    gio_ket_thuc TIME NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT FK_LICH_BACSI FOREIGN KEY (ma_bac_si)
    REFERENCES nhanvien (ma_nv)
);

-- ALTER TABLE KHOA
-- ADD CONSTRAINT FK_KHOA_NHANVIEN FOREIGN KEY (TruongKhoa) REFERENCES NHANVIEN (MaNV);

INSERT INTO nhanvien (ma_nv,ma_chung_chi,so_dt_nv,ho_ten_nv,ngay_sinh_nv,gioi_tinh_nv,dia_chi_nv,chuc_vu,kinh_nghiem,ma_khoa,created_at,created_by,updated_at,updated_by) VALUES
    ('BS-2508114ROHDGI','123456','0913456789','Ng Van BS','1982-04-21','NAM','213 AFDA P2','BACSI','[{"benhVien":"A","chuyenKhoa":"CK2","viTri":"Bac Si Dieu Tri","namBatDau":2010,"namKetThuc":2021}]',NULL,'2025-08-11 14:32:03','employees-service',NULL,NULL),
    ('DS-250811IEG9XJF',NULL,'0223456789','Ng Van DS','1989-06-18','NAM','213 AFDA P2','DUOCSI',NULL,NULL,'2025-08-11 14:29:24','employees-service',NULL,NULL),
    ('TT-250811FY2N8G1',NULL,'0213456789','Ng Van TT','1993-11-20','NAM','213 AFDA P2','TIEPTAN',NULL,NULL,'2025-08-11 14:28:42','employees-service',NULL,NULL);

INSERT INTO nhanvien (ma_nv, ma_chung_chi, so_dt_nv, ho_ten_nv, ngay_sinh_nv, gioi_tinh_nv, dia_chi_nv, chuc_vu, kinh_nghiem, created_at, created_by)
VALUES
-- Bác sĩ
('BS-250824ABC123X', 'CCBS001', '0911111111', 'Nguyen Van Bac', '1980-05-12', 'NAM', 'Hà Nội', 'BACSI',
 '[{"benhVien":"Benh Vien A","chuyenKhoa":"CK2","viTri":"Bac Si Dieu Tri","namBatDau":2010,"namKetThuc":2021}]',
 NOW(), 'employees-service'),

('BS-250824JKL789M', 'CCBS002', '0944444444', 'Pham Thi Hoa', '1983-11-20', 'NU', 'TP HCM', 'BACSI',
 '[{"benhVien":"Benh Vien B","chuyenKhoa":"CK1","viTri":"Bac Si Noi Tru","namBatDau":2012,"namKetThuc":2019}]',
 NOW(), 'employees-service'),

('BS-250824XYZ456Q', 'CCBS003', '0977777777', 'Le Van An', '1975-07-30', 'NAM', 'Đà Nẵng', 'BACSI',
 '[{"benhVien":"Benh Vien C","chuyenKhoa":"CK3","viTri":"Truong Khoa","namBatDau":2005,"namKetThuc":2020}]',
 NOW(), 'employees-service'),

-- Dược sĩ
('DS-250824QWE789Z', NULL, '0922222222', 'Tran Thi Duoc', '1985-08-22', 'NU', 'TP HCM', 'DUOCSI', NULL,
 NOW(), 'employees-service'),

('DS-250824RTY123P', NULL, '0955555555', 'Nguyen Van Thuoc', '1990-03-14', 'NAM', 'Hải Phòng', 'DUOCSI', NULL,
 NOW(), 'employees-service'),

-- Tiếp tân
('TT-250824LMN456K', NULL, '0933333333', 'Le Van Tan', '1992-02-10', 'NAM', 'Đà Nẵng', 'TIEPTAN', NULL,
 NOW(), 'employees-service'),

('TT-250824POI852V', NULL, '0966666666', 'Pham Thi Lan', '1995-09-05', 'NU', 'Cần Thơ', 'TIEPTAN', NULL,
 NOW(), 'employees-service');

CREATE TABLE IF NOT EXISTS khoa (
    ma_khoa INT PRIMARY KEY AUTO_INCREMENT,
    ten_khoa VARCHAR(50),
    gioi_thieu_khoa TEXT,
    truong_khoa VARCHAR(20) DEFAULT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS nhanvien (
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

CREATE TABLE IF NOT EXISTS bangcap (
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

CREATE TABLE IF NOT EXISTS lichlamviec (
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
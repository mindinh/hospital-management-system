--DROP TABLE IF EXISTS dichvu;
--DROP TABLE IF EXISTS phieukham;
--DROP TABLE IF EXISTS lichkham;

CREATE TABLE IF NOT EXISTS dichvu (
    ma_dich_vu INT PRIMARY KEY AUTO_INCREMENT,
    ten_dich_vu VARCHAR(100),
    mo_ta_dich_vu TEXT,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS lichkham (
    ma_lich_kham VARCHAR(16) PRIMARY KEY,
    ma_bac_si VARCHAR(16),
    ma_benh_nhan VARCHAR(16),
    ngay_kham DATE,
    gio_kham TIME,
    ghi_chu TEXT,
    tinh_trang ENUM('DA_DAT', 'DA_HUY', 'CHUA_THANH_TOAN', 'DA_THANH_TOAN'),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS phieukham (
    ma_phieu VARCHAR(16) PRIMARY KEY,
    ma_lich_kham VARCHAR(16),
    ten_bac_si VARCHAR(30),
    ten_benh_nhan VARCHAR(30),
    ngay_kham DATE,
    gio_kham TIME,
    so_thu_tu INT,
    so_phong CHAR(4),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT FK_PHIEUKHAM_LICHKHAM FOREIGN KEY (ma_lich_kham)
    REFERENCES lichkham (ma_lich_kham)
);


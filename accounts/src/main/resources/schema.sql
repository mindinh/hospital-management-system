CREATE TABLE IF NOT EXISTS taikhoan (
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
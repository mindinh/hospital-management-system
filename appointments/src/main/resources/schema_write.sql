DROP TABLE IF EXISTS dichvu;
DROP TABLE IF EXISTS phieukham;
DROP TABLE IF EXISTS lichkham;

CREATE TABLE dichvu (
    ma_dich_vu INT PRIMARY KEY AUTO_INCREMENT,
    ten_dich_vu VARCHAR(100),
    mo_ta_dich_vu TEXT,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE lichkham (
    ma_lich_kham VARCHAR(16) PRIMARY KEY,
    ma_bac_si VARCHAR(16),
    ma_benh_nhan VARCHAR(16),
    ngay_kham DATE,
    gio_kham TIME,
    ghi_chu TEXT,
    tinh_trang ENUM('DA_DAT', 'DA_HUY', 'CHUA_THANH_TOAN', 'DA_THANH_TOAN', 'DA_KHAM'),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE phieukham (
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

INSERT INTO dichvu (ten_dich_vu, mo_ta_dich_vu, created_at, created_by) VALUES
-- Xét nghiệm
('Xét nghiệm công thức máu', 'Kiểm tra số lượng và chất lượng các tế bào máu.', NOW(), 'appointments-service'),
('Xét nghiệm đường huyết', 'Đo lượng đường trong máu để phát hiện tiểu đường.', NOW(), 'appointments-service'),
('Xét nghiệm chức năng gan', 'Kiểm tra các chỉ số men gan (AST, ALT…).', NOW(), 'appointments-service'),
('Xét nghiệm chức năng thận', 'Đánh giá hoạt động của thận thông qua ure, creatinine.', NOW(), 'appointments-service'),
('Xét nghiệm mỡ máu', 'Kiểm tra cholesterol và triglyceride.', NOW(), 'appointments-service'),

-- Chẩn đoán hình ảnh
('Siêu âm ổ bụng', 'Siêu âm kiểm tra gan, thận, mật, tụy, lách.', NOW(), 'appointments-service'),
('Siêu âm tim', 'Siêu âm để đánh giá chức năng tim và van tim.', NOW(), 'appointments-service'),
('X-quang ngực', 'Chụp X-quang kiểm tra phổi, tim và xương sườn.', NOW(), 'appointments-service'),
('CT sọ não', 'Chụp cắt lớp vi tính để phát hiện tổn thương não.', NOW(), 'appointments-service'),
('MRI cột sống thắt lưng', 'Chụp cộng hưởng từ phát hiện thoát vị đĩa đệm.', NOW(), 'appointments-service'),

-- Thủ thuật, điều trị
('Nội soi dạ dày', 'Nội soi phát hiện viêm loét dạ dày, HP.', NOW(), 'appointments-service'),
('Nội soi đại tràng', 'Nội soi phát hiện polyp, viêm, loét đại tràng.', NOW(), 'appointments-service'),
('Điện tâm đồ (ECG)', 'Ghi lại hoạt động điện của tim.', NOW(), 'appointments-service'),
('Siêu âm Doppler mạch máu', 'Kiểm tra lưu thông của mạch máu.', NOW(), 'appointments-service'),
('Đo điện não đồ (EEG)', 'Ghi lại hoạt động điện của não.', NOW(), 'appointments-service'),

-- Thủ thuật nhỏ
('Rửa tai', 'Làm sạch ống tai, loại bỏ ráy tai.', NOW(), 'appointments-service'),
('Đặt sonde tiểu', 'Thủ thuật đặt ống thông tiểu.', NOW(), 'appointments-service'),
('Khâu vết thương nhỏ', 'Khâu xử lý vết thương ngoài da.', NOW(), 'appointments-service'),
('Băng bó cố định gãy xương', 'Băng bó hoặc nẹp để cố định tạm thời xương gãy.', NOW(), 'appointments-service'),
('Tiêm thuốc giảm đau', 'Tiêm thuốc giảm đau theo chỉ định.', NOW(), 'appointments-service');

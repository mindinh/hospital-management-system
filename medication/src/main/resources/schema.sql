DROP TABLE IF EXISTS chitiet_donthuoc;
DROP TABLE IF EXISTS donthuoc;
DROP TABLE IF EXISTS thuoc;

CREATE TABLE thuoc (
    ma_thuoc VARCHAR(16) PRIMARY KEY,
    so_dk_thuoc VARCHAR(20),
    ten_thuoc VARCHAR(50),
    mo_ta_thuoc TEXT,
    dieu_tri TEXT,
    loai_thuoc VARCHAR(10),
    so_luong INT,
    tinh_trang ENUM('ACTIVE', 'INACTIVE', 'DELETED'),

    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE donthuoc (
    ma_don_thuoc VARCHAR(16) PRIMARY KEY,
    ma_bac_si VARCHAR(16),
    ma_benh_nhan VARCHAR(16),
    ghi_chu TEXT,
    ngay_cap DATETIME,
    tinh_trang ENUM('DOI_LAY_THUOC', 'DA_SAN_SANG', 'DA_NHAN'),

    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE chitiet_donthuoc (
    ma_chi_tiet VARCHAR(16) PRIMARY KEY,
    ma_don_thuoc VARCHAR(16),
    ma_thuoc VARCHAR(16),
    ten_thuoc VARCHAR(50),
    so_luong INT,
    chi_dinh TEXT,

    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT FK_CTDT_DT FOREIGN KEY (ma_don_thuoc)
    REFERENCES donthuoc(ma_don_thuoc),
    CONSTRAINT FK_CTDT_THUOC FOREIGN KEY (ma_thuoc)
    REFERENCES thuoc(ma_thuoc)

);

INSERT INTO thuoc (ma_thuoc, so_dk_thuoc, ten_thuoc, mo_ta_thuoc, dieu_tri, loai_thuoc, so_luong, created_at, created_by)
VALUES
    ('TH001', 'VN-12345-01', 'Paracetamol 500mg', 'Thuốc giảm đau, hạ sốt thông dụng', 'Giảm đau, hạ sốt nhẹ đến vừa', 'Viên', 200, NOW(), 'medication-service'),
    ('TH002', 'VN-67890-02', 'Amoxicillin 500mg', 'Kháng sinh nhóm penicillin', 'Nhiễm khuẩn hô hấp, tai mũi họng, tiết niệu', 'Viên', 150, NOW(), 'medication-service'),
    ('TH003', 'VN-24680-03', 'Vitamin C 1000mg', 'Bổ sung vitamin C', 'Tăng sức đề kháng, hỗ trợ điều trị cảm cúm', 'Viên sủi', 300, NOW(), 'medication-service'),
    ('TH004', 'VN-13579-04', 'Omeprazole 20mg', 'Thuốc ức chế bơm proton', 'Trào ngược dạ dày, loét dạ dày tá tràng', 'Viên', 120, NOW(), 'medication-service'),
    ('TH005', 'VN-11223-05', 'Acetylcystein 200mg', 'Thuốc long đờm', 'Các bệnh hô hấp có đờm đặc quánh', 'Gói bột', 250, NOW(), 'medication-service'),
    ('TH006', 'VN-33445-06', 'Ibuprofen 400mg', 'Thuốc chống viêm không steroid', 'Giảm đau, hạ sốt, chống viêm', 'Viên', 180, NOW(), 'medication-service'),
    ('TH007', 'VN-55667-07', 'Cefuroxime 500mg', 'Kháng sinh nhóm cephalosporin', 'Nhiễm khuẩn hô hấp, tiết niệu, da', 'Viên', 100, NOW(), 'medication-service'),
    ('TH008', 'VN-77889-08', 'Loratadine 10mg', 'Kháng histamin thế hệ 2', 'Viêm mũi dị ứng, mày đay', 'Viên', 220, NOW(), 'medication-service'),
    ('TH009', 'VN-99001-09', 'Metformin 500mg', 'Thuốc hạ đường huyết nhóm biguanid', 'Đái tháo đường type 2', 'Viên', 160, NOW(), 'medication-service'),
    ('TH010', 'VN-22334-10', 'Aspirin 81mg', 'Thuốc chống kết tập tiểu cầu liều thấp', 'Dự phòng nhồi máu cơ tim, đột quỵ', 'Viên', 190, NOW(), 'medication-service'),
    ('TH011', 'VN-44556-11', 'Clarithromycin 500mg', 'Kháng sinh nhóm macrolid', 'Nhiễm khuẩn hô hấp, loét dạ dày do H.pylori', 'Viên', 140, NOW(), 'medication-service'),
    ('TH012', 'VN-66778-12', 'Cetirizine 10mg', 'Kháng histamin thế hệ 2', 'Viêm mũi dị ứng, ngứa, mày đay', 'Viên', 200, NOW(), 'medication-service'),
    ('TH013', 'VN-88990-13', 'Prednisolone 5mg', 'Corticoid', 'Viêm, dị ứng, suy tuyến thượng thận', 'Viên', 100, NOW(), 'medication-service'),
    ('TH014', 'VN-10112-14', 'Salbutamol 2mg', 'Thuốc giãn phế quản', 'Hen phế quản, COPD', 'Viên', 80, NOW(), 'medication-service'),
    ('TH015', 'VN-12131-15', 'Losartan 50mg', 'Thuốc ức chế thụ thể angiotensin II', 'Tăng huyết áp, suy tim', 'Viên', 170, NOW(), 'medication-service'),
    ('TH016', 'VN-14151-16', 'Atorvastatin 20mg', 'Thuốc hạ mỡ máu nhóm statin', 'Rối loạn lipid máu, phòng ngừa biến cố tim mạch', 'Viên', 130, NOW(), 'medication-service'),
    ('TH017', 'VN-16171-17', 'Furosemide 40mg', 'Thuốc lợi tiểu quai', 'Phù do suy tim, suy thận, xơ gan', 'Viên', 110, NOW(), 'medication-service'),
    ('TH018', 'VN-18191-18', 'Spironolactone 25mg', 'Thuốc lợi tiểu giữ kali', 'Suy tim, tăng huyết áp, phù', 'Viên', 90, NOW(), 'medication-service'),
    ('TH019', 'VN-20212-19', 'Ranitidine 150mg', 'Thuốc kháng H2', 'Loét dạ dày, trào ngược dạ dày-thực quản', 'Viên', 100, NOW(), 'medication-service'),
    ('TH020', 'VN-22232-20', 'Dextromethorphan 15mg', 'Thuốc giảm ho', 'Ho khan, viêm họng', 'Viên', 210, NOW(), 'medication-service'),
    ('TH021', 'VN-24252-21', 'Domperidone 10mg', 'Thuốc chống nôn, tăng nhu động dạ dày', 'Buồn nôn, nôn, đầy bụng khó tiêu', 'Viên', 120, NOW(), 'medication-service'),
    ('TH022', 'VN-26272-22', 'Clopidogrel 75mg', 'Thuốc chống kết tập tiểu cầu', 'Ngừa đột quỵ, nhồi máu cơ tim', 'Viên', 140, NOW(), 'medication-service'),
    ('TH023', 'VN-28292-23', 'Insulin Glargine', 'Insulin tác dụng kéo dài', 'Đái tháo đường type 1, type 2', 'Lọ tiêm', 60, NOW(), 'medication-service'),
    ('TH024', 'VN-30313-24', 'Diazepam 5mg', 'Benzodiazepin', 'Lo âu, mất ngủ, co giật', 'Viên', 70, NOW(), 'medication-service'),
    ('TH025', 'VN-32333-25', 'Diclofenac 50mg', 'Thuốc giảm đau, chống viêm', 'Đau khớp, viêm cơ, đau sau phẫu thuật', 'Viên', 150, NOW(), 'medication-service'),
    ('TH026', 'VN-34353-26', 'Hydroxyzine 25mg', 'Kháng histamin, an thần', 'Dị ứng, ngứa, lo âu nhẹ', 'Viên', 100, NOW(), 'medication-service'),
    ('TH027', 'VN-36373-27', 'Warfarin 5mg', 'Thuốc chống đông đường uống', 'Huyết khối tĩnh mạch sâu, rung nhĩ', 'Viên', 80, NOW(), 'medication-service'),
    ('TH028', 'VN-38393-28', 'Levothyroxine 50mcg', 'Hormone tuyến giáp tổng hợp', 'Suy giáp', 'Viên', 120, NOW(), 'medication-service'),
    ('TH029', 'VN-40414-29', 'Azithromycin 500mg', 'Kháng sinh nhóm macrolid', 'Nhiễm khuẩn hô hấp, tiết niệu, da', 'Viên', 130, NOW(), 'medication-service'),
    ('TH030', 'VN-42434-30', 'Gliclazide 80mg', 'Thuốc hạ đường huyết nhóm sulfonylurea', 'Đái tháo đường type 2', 'Viên', 150, NOW(), 'medication-service');

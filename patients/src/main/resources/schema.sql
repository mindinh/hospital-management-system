DROP TABLE IF EXISTS hoso_benhnhan;
DROP TABLE IF EXISTS benhnhan;

CREATE TABLE benhnhan (
    ma_benh_nhan VARCHAR(16),
    so_dt_bn CHAR(10),
    ho_ten_bn VARCHAR(30),
    ngay_sinh_bn DATE,
    gioi_tinh_bn ENUM('NAM', 'NU'),
    dia_chi_bn VARCHAR(50),
    so_bhyt_bn VARCHAR(50),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,

    CONSTRAINT PK_BENHNHAN PRIMARY KEY (ma_benh_nhan)
);


CREATE TABLE hoso_benhnhan (
    ma_ho_so INT PRIMARY KEY AUTO_INCREMENT,
    ma_benh_nhan VARCHAR(20) NOT NULL,
    ngay_kham DATETIME NOT NULL,
    ly_do_kham TEXT,
    trieu_chung TEXT,
    chan_doan TEXT,
    ghi_chu_dieu_tri TEXT,
    ma_bac_si VARCHAR(20) NOT NULL,


    CONSTRAINT FK_HOSOBENHAN_BENHNHAN FOREIGN KEY (ma_benh_nhan)
    REFERENCES benhnhan (ma_benh_nhan)
);

INSERT INTO benhnhan (ma_benh_nhan,so_dt_bn,ho_ten_bn,ngay_sinh_bn,gioi_tinh_bn,dia_chi_bn,so_bhyt_bn,created_at,created_by,updated_at,updated_by) VALUES
    ('BN-250816Y63OUCH','0923456789','Tran Van Bn','2000-02-17','NAM','BHYT100','Ho Chi Minh','2025-08-11 14:09:37','patient-service',NULL,NULL),
    ('BN-250818QESRHVJ','0908345678','Le Van Bn','2002-12-07','NAM','BHYT021','Ho Chi Minh','2025-08-12 14:09:37','patient-service',NULL,NULL),
    ('BN-250819ABC123X','0911111111','Bn 001','1995-03-12','NAM','Ha Noi','BHYT001','2025-08-19 09:15:24','patient-service',NULL,NULL),
    ('BN-250819XYZ789K','0911111112','Bn 002','1998-07-21','NU','Hai Phong','BHYT002','2025-08-19 10:20:10','patient-service',NULL,NULL),
    ('BN-250820LMN456Q','0911111113','Bn 003','2001-11-05','NAM','Da Nang','BHYT003','2025-08-20 08:05:42','patient-service',NULL,NULL),
    ('BN-250820PQR852Z','0911111114','Bn 004','1999-04-17','NU','Ho Chi Minh','BHYT004','2025-08-20 12:40:33','patient-service',NULL,NULL),
    ('BN-250820JKL963T','0911111115','Bn 005','2003-09-09','NAM','Can Tho','BHYT005','2025-08-20 15:22:55','patient-service',NULL,NULL),
    ('BN-250821GHI741M','0911111116','Bn 006','1997-01-28','NU','Hue','BHYT006','2025-08-21 09:13:18','patient-service',NULL,NULL),
    ('BN-250821UVW258B','0911111117','Bn 007','2000-06-13','NAM','Nam Dinh','BHYT007','2025-08-21 11:47:29','patient-service',NULL,NULL),
    ('BN-250821DEF369L','0911111118','Bn 008','1996-12-03','NU','Ninh Binh','BHYT008','2025-08-21 16:22:40','patient-service',NULL,NULL),
    ('BN-250822QAZ159W','0911111119','Bn 009','2002-05-27','NAM','Quang Nam','BHYT009','2025-08-22 08:05:12','patient-service',NULL,NULL),
    ('BN-250822WSX753E','0911111120','Bn 010','1994-08-14','NU','Vinh','BHYT010','2025-08-22 09:45:44','patient-service',NULL,NULL),
    ('BN-250822EDC456R','0911111121','Bn 011','1993-03-19','NAM','Bac Ninh','BHYT011','2025-08-22 11:33:17','patient-service',NULL,NULL),
    ('BN-250822RFV852N','0911111122','Bn 012','2001-12-25','NU','Thai Binh','BHYT012','2025-08-22 14:26:58','patient-service',NULL,NULL),
    ('BN-250822TGB147Y','0911111123','Bn 013','1990-10-30','NAM','Thanh Hoa','BHYT013','2025-08-22 18:44:21','patient-service',NULL,NULL),
    ('BN-250823YHN951C','0911111124','Bn 014','2004-01-09','NU','Nghe An','BHYT014','2025-08-23 07:50:32','patient-service',NULL,NULL),
    ('BN-250823UJM357O','0911111125','Bn 015','1992-07-15','NAM','Phu Tho','BHYT015','2025-08-23 09:13:08','patient-service',NULL,NULL),
    ('BN-250823IKM258P','0911111126','Bn 016','1998-02-11','NU','Ha Tinh','BHYT016','2025-08-23 11:45:19','patient-service',NULL,NULL),
    ('BN-250823OLP654Q','0911111127','Bn 017','1991-06-29','NAM','Lang Son','BHYT017','2025-08-23 13:22:41','patient-service',NULL,NULL),
    ('BN-250823QWE963S','0911111128','Bn 018','1999-09-20','NU','Lao Cai','BHYT018','2025-08-23 15:30:25','patient-service',NULL,NULL),
    ('BN-250823RTY741D','0911111129','Bn 019','1997-04-08','NAM','Dak Lak','BHYT019','2025-08-23 17:45:11','patient-service',NULL,NULL),
    ('BN-250823FGH258A','0911111130','Bn 020','2000-12-01','NU','Khanh Hoa','BHYT020','2025-08-23 19:05:54','patient-service',NULL,NULL);;




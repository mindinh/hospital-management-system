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
    ('BN-250816Y63OUCH','0923456789','Tran Van Bn','2000-02-17','NAM',NULL,NULL,'2025-08-11 14:09:37','patient-service',NULL,NULL);




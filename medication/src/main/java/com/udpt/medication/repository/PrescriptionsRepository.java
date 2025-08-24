package com.udpt.medication.repository;

import com.udpt.medication.dto.PrescriptionDto;
import com.udpt.medication.dto.PrescriptionFlatDto;
import com.udpt.medication.entity.PrescriptionEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<PrescriptionEntity, String>, JpaSpecificationExecutor<PrescriptionEntity> {
    List<PrescriptionEntity> findByPatientId(String patientId);

    List<PrescriptionEntity> findAll();

    @Query("""
    SELECT new com.udpt.medication.dto.PrescriptionDetailDto(
        pd.medication.id,
        pd.medName,
        pd.quantity,
        pd.indication
    )
    FROM PrescriptionEntity p
    JOIN PrescriptionDetailEntity pd
    ON p.id = pd.prescription.id
    WHERE p.patientId = :maBenhNhan
    """)
    List<PrescriptionDto> findPrescriptionDetails(@Param("maBenhNhan") String maBenhNhan);

    @Query(value = """
    SELECT 
        d.ma_don_thuoc AS maDonThuoc,
        d.ma_bac_si AS maBacSi,
        d.ma_benh_nhan AS maBenhNhan,
        d.ghi_chu AS ghiChu,
        DATE_FORMAT(d.ngay_cap, '%Y/%m/%d %H:%i:%s') AS ngayCap,
        d.tinh_trang AS tinhTrang,
        ct.ma_chi_tiet AS maChiTiet,
        ct.ma_thuoc AS maThuoc,
        ct.ten_thuoc AS tenThuoc,
        ct.so_luong AS soLuong,
        ct.chi_dinh AS chiDinh
    FROM donthuoc d
    LEFT JOIN chitiet_donthuoc ct
    ON d.ma_don_thuoc = ct.ma_don_thuoc 
    WHERE d.ma_benh_nhan = :maBenhNhan
    """, nativeQuery = true)
    List<PrescriptionFlatDto> findPrescriptionsWithDetails(@Param("maBenhNhan") String maBenhNhan);

}

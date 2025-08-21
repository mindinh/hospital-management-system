package com.udpt.medication.repository;

import com.udpt.medication.dto.PrescriptionDto;
import com.udpt.medication.dto.PrescriptionFlatDto;
import com.udpt.medication.entity.PrescriptionEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<PrescriptionEntity, String> {
    List<PrescriptionEntity> findByPatientId(String patientId);

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
        p.id AS prescriptionId,
        p.doctorId AS maBacSi,
        p.patientId AS maBenhNhan,
        p.notes AS ghiChu,
        pd.medication.id AS thuocId,
        pd.medication.medicationName AS tenThuoc,
        pd.quantity AS soLuong,
        pd.indication AS lieuDung
    FROM PrescriptionEntity p
    LEFT JOIN PrescriptionDetailEntity pd 
    ON p.id = pd.prescription.id
    WHERE p.patientId = :maBenhNhan
    """, nativeQuery = true)
    List<PrescriptionFlatDto> findPrescriptionsWithDetails(@Param("maBenhNhan") String maBenhNhan);

}

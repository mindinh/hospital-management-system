package com.udpt.medication.request;

import com.udpt.medication.dto.PrescriptionDetailDto;

import java.util.List;

public record CreatePrescriptionRequest(
        String maBacSi,
        String maBenhNhan,
        String ghiChu,
        List<PrescriptionDetailDto> prescriptionDetails
) {
}

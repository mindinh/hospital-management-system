package com.udpt.medication.mapper;

import com.udpt.medication.dto.PrescriptionDetailDto;
import com.udpt.medication.entity.PrescriptionDetailEntity;

public class PrescriptionDetailMapper {
    private PrescriptionDetailMapper() {}

    public static PrescriptionDetailDto mapToDto(PrescriptionDetailEntity entity, PrescriptionDetailDto dto) {
        dto.setMaThuoc(entity.getId());
        dto.setTenThuoc(entity.getMedName());
        dto.setSoLuong(entity.getQuantity());
        dto.setChiDinh(entity.getIndication());

        return dto;
    }
}

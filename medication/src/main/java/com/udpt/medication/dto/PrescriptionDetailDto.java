package com.udpt.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrescriptionDetailDto {
    private String maThuoc;
    private String tenThuoc;
    private int soLuong;
    private String chiDinh;

}

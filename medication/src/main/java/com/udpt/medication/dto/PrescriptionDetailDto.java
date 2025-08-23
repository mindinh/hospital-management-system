package com.udpt.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionDetailDto {
    private String maThuoc;
    private String tenThuoc;
    private int soLuong;
    private String chiDinh;

}

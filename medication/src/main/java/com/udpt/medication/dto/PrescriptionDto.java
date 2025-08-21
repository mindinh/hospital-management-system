package com.udpt.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PrescriptionDto {

    private String maBacSi;
    private String maBenhNhan;
    private String ghiChu;
    private String ngayCap;
    private List<PrescriptionDetailDto> prescriptionDetails;

}

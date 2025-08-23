package com.udpt.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionDto {

    private String maBacSi;
    private String maBenhNhan;
    private String ghiChu;
    private String ngayCap;
    private List<PrescriptionDetailDto> prescriptionDetails;

    public PrescriptionDto(String maBacSi,  String maBenhNhan, String ghiChu, String ngayCap) {
        this.maBacSi = maBacSi;
        this.maBenhNhan = maBenhNhan;
        this.ghiChu = ghiChu;
        this.ngayCap = ngayCap;
    }

}

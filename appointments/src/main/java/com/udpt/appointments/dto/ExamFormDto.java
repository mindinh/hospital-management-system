package com.udpt.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ExamFormDto {
    private String maPhieu;
    private String tenBacSi;
    private String tenBenhNhan;
    private String ngay;
    private String gio;
    private String soPhong;
    private String soThuTu;
}

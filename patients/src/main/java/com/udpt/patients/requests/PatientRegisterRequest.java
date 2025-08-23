package com.udpt.patients.requests;

import lombok.Data;

@Data
public class PatientRegisterRequest {
    private String maBenhNhan;
    private String soDienThoai;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String bhyt;
}

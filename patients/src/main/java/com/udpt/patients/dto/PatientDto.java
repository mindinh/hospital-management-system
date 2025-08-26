package com.udpt.patients.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PatientDto {

    private String maBenhNhan;

    private String soDienThoai;

    private String emailBenhNhan;

    private String hoTen;

    private String ngaySinh;

    private String gioiTinh;

}

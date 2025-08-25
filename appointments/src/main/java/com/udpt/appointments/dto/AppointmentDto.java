package com.udpt.appointments.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class AppointmentDto {
    private String maLichKham;
    private String maBacSi;
    private String tenBacSi;
    private String chuyenKhoa;
    private String maBenhNhan;
    private String tenBenhNhan;
    private String soDienThoaiBenhNhan;
    private LocalDate ngayKham;
    private LocalTime gioKham;
    private String tinhTrang;

}

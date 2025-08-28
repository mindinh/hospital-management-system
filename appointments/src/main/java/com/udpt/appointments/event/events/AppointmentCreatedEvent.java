package com.udpt.appointments.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreatedEvent implements Serializable {
    private String maLichKham;
    private String maBenhNhan;
    private String tenBenhNhan;
    private String soDTBenhNhan;
    private String emailBenhNhan;
    private String maBacSi;
    private String tenBacSi;
    private String chuyenKhoa;
    private String ghiChu;
    private String soPhong;
    private String tinhTrang;
    private LocalDate ngayKham;
    private LocalTime gioKham;
}

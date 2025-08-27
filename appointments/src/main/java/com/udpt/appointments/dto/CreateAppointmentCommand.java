package com.udpt.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateAppointmentCommand {

    private LocalDate ngayKham;
    private String maBenhNhan;
    private String maBacSi;
    private LocalTime gioKham;
    private String ghiChu;
    private String phong;

}

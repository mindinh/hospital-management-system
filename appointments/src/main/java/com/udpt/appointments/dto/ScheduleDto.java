package com.udpt.appointments.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDto {

    private String maBacSi;
    private LocalDate ngayLamViec;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;

}

package com.udpt.employees.service;

import com.udpt.employees.dto.DoctorScheduleDto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ISchedulesService {
    DoctorScheduleDto getDoctorSchedule(String maBacSi);
    void createSchedule(String maBacSi, LocalDate ngayLamViec, LocalTime batDau, LocalTime ketThuc);
}

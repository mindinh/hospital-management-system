package com.udpt.employees.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorScheduleDto {
    private String maBacSi;
    private String hoTenBacSi;
    private String chuyenKhoa;
    private List<ScheduleDto> lichLamViec;
}

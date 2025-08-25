package com.udpt.appointments.response;

import com.udpt.appointments.dto.ScheduleDto;
import lombok.Data;

import java.util.List;

@Data
public class DoctorScheduleResponse {
    private String maBacSi;
    private String hoTenBacSi;
    private String chuyenKhoa;
    private List<ScheduleDto> lichLamViec;
}

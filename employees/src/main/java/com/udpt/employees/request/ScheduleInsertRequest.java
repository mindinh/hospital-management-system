package com.udpt.employees.request;

import com.udpt.employees.dto.ScheduleDto;

import java.util.List;

public record ScheduleInsertRequest(
        String maBacSi,
        List<ScheduleDto> lichLamViec
) {
}

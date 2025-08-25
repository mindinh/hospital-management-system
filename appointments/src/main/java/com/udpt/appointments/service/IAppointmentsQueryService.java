package com.udpt.appointments.service;


import com.udpt.appointments.dto.AppointmentDto;
import com.udpt.appointments.dto.MonthlyPatientStatisticDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentsQueryService {
    List<MonthlyPatientStatisticDTO> countPatientsByMonth(int year);
    Page<AppointmentDto> searchAppointments(String doctorId, String patientId, LocalDate startDate, LocalDate endDate, String status, int page, int size);
}

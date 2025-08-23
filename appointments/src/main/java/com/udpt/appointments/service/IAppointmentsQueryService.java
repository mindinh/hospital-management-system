package com.udpt.appointments.service;


import com.udpt.appointments.dto.AppointmentDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IAppointmentsQueryService {
    int countPatientsByDoctorAndDateRange(String maBacSi, LocalDate startDate, LocalDate endDate);
    int countPatientsByDateRange(LocalDate startDate, LocalDate endDate);
    Page<AppointmentDto> searchAppointments(String doctorId, String patientId, LocalDate startDate, LocalDate endDate, int page, int size);
}

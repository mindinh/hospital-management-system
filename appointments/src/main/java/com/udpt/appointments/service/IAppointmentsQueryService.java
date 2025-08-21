package com.udpt.appointments.service;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IAppointmentsQueryService {
    int countPatientsByDoctorAndDateRange(String maBacSi, LocalDate startDate, LocalDate endDate);
}

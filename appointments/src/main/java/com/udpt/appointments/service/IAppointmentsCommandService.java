package com.udpt.appointments.service;

import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.dto.ExamFormDto;
import com.udpt.appointments.request.AppointmentInsertRequest;
import org.springframework.security.core.Authentication;

public interface IAppointmentsCommandService {
    void createAppointment(CreateAppointmentCommand command);
    void bookAppointment(CreateAppointmentCommand command, Authentication authentication);
    ExamFormDto checkinAppointment(String id);
    void cancelAppointment(String id);
}

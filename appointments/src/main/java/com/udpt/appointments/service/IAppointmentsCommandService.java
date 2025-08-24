package com.udpt.appointments.service;

import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.request.AppointmentInsertRequest;
import org.springframework.security.core.Authentication;

public interface IAppointmentsCommandService {
    void createAppointment(CreateAppointmentCommand command);
    void bookAppointment(CreateAppointmentCommand command, Authentication authentication);
    boolean checkinAppointment(String id);
    boolean cancelAppointment(String id);
}

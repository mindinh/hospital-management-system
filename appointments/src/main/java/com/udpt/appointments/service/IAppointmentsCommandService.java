package com.udpt.appointments.service;

import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.request.AppointmentInsertRequest;

public interface IAppointmentsCommandService {
    void createAppointment(CreateAppointmentCommand command);
}

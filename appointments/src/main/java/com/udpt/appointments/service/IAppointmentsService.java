package com.udpt.appointments.service;

import com.udpt.appointments.request.AppointmentInsertRequest;

public interface IAppointmentsService {
    void createAppointment(AppointmentInsertRequest request);
}

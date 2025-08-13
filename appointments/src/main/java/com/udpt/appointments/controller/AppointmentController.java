package com.udpt.appointments.controller;


import com.udpt.appointments.service.IAppointmentsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppointmentController {

    private IAppointmentsService appointmentsService;
    public AppointmentController(IAppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }



}

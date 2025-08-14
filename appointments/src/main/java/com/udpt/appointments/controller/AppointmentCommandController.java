package com.udpt.appointments.controller;


import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.dto.ResponseDto;
import com.udpt.appointments.service.IAppointmentsCommandService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppointmentCommandController {

    private IAppointmentsCommandService appointmentsCommandService;
    public AppointmentCommandController(IAppointmentsCommandService appointmentsService) {
        this.appointmentsCommandService = appointmentsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentCommand command) {
        appointmentsCommandService.createAppointment(command);

        return ResponseEntity.ok(new ResponseDto("200", "Appointment booked successfully"));
    }

}

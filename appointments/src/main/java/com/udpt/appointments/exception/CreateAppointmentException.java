package com.udpt.appointments.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CreateAppointmentException extends RuntimeException {
    public CreateAppointmentException(String message) {

        super(message);
    }

}

package com.udpt.medication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CreatePrescriptionException extends RuntimeException {
    public CreatePrescriptionException(String message) {
        super(message);

    }
}

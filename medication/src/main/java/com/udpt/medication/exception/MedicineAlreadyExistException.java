package com.udpt.medication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MedicineAlreadyExistException extends RuntimeException {
    public MedicineAlreadyExistException(String message) {
        super(message);

    }
}

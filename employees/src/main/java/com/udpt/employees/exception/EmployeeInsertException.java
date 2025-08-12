package com.udpt.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeInsertException extends RuntimeException {
    public EmployeeInsertException(String message) {
        super(message);

    }
}

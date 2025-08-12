package com.udpt.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentAlreadyExistException extends RuntimeException {
    public DepartmentAlreadyExistException(String message) {
        super(message);

    }
}

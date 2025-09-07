package com.udpt.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/appointments")
    public ResponseEntity<?> appointmentsFallback() {

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Đã xảy ra lỗi. Vui lòng chờ một thời gian trước khi thử lại.");
    }
}

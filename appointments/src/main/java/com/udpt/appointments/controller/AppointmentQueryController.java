package com.udpt.appointments.controller;


import com.udpt.appointments.dto.ResponseDto;
import com.udpt.appointments.service.IAppointmentsQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppointmentQueryController {

    private IAppointmentsQueryService appointmentsQueryService;
    public AppointmentQueryController(IAppointmentsQueryService appointmentsQueryService) {
        this.appointmentsQueryService = appointmentsQueryService;
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> patientStatistic(@RequestParam String maBacSi,
                                              @RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate) {
        int count = appointmentsQueryService.countPatientsByDoctorAndDateRange(maBacSi, startDate, endDate);
        Map<String, Object> body = Map.of(
                "maBacSi", maBacSi,
                "startDate", startDate,
                "endDate", endDate,
                "soLuongBenhNhan", count
        );

        return ResponseEntity.ok(body);
    }

}

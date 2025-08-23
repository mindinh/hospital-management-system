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

    public ResponseEntity<?> searchAppointments(
            @RequestParam(required = false) String maBS,
            @RequestParam(required = false) String maBN,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKhamTu,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKhamDen,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(appointmentsQueryService.searchAppointments(maBS, maBN, ngayKhamTu, ngayKhamDen, page, size));
    }

    @GetMapping("/statistic/doctor")
    public ResponseEntity<?> patientStatisticByDoctor(@RequestParam String maBacSi,
                                              @RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate) {
        int count = appointmentsQueryService.countPatientsByDoctorAndDateRange(maBacSi, startDate, endDate);

        if (count == 0) {
            Map<String, Object> message = Map.of(
                    "message", "No patients found for doctor " + maBacSi +
                            " from " + startDate  + " to " + endDate
            );
            return ResponseEntity.ok(message);
        }

        Map<String, Object> body = Map.of(
                "maBacSi", maBacSi,
                "startDate", startDate,
                "endDate", endDate,
                "soLuongBenhNhan", count
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> patientStatistic(@RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate) {
        int count = appointmentsQueryService.countPatientsByDateRange(startDate, endDate);

        if (count == 0) {
            Map<String, Object> message = Map.of(
                    "message", "No patients found "+
                            " from " + startDate  + " to " + endDate
            );
            return ResponseEntity.ok(message);
        }

        Map<String, Object> body = Map.of(
                "startDate", startDate,
                "endDate", endDate,
                "soLuongBenhNhan", count
        );
        return ResponseEntity.ok(body);
    }
}

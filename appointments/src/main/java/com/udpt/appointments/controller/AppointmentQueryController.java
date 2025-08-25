package com.udpt.appointments.controller;


import com.udpt.appointments.dto.MonthlyPatientStatisticDTO;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppointmentQueryController {

    private IAppointmentsQueryService appointmentsQueryService;
    public AppointmentQueryController(IAppointmentsQueryService appointmentsQueryService) {
        this.appointmentsQueryService = appointmentsQueryService;
    }

    @GetMapping("/my-appointments")
    public ResponseEntity<?> getMyAppointments(
            @RequestParam(required = false) String maBN,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKhamTu,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKhamDen,
            @RequestParam(required = false, defaultValue = "DA_DAT") String tinhTrang,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(appointmentsQueryService.searchAppointments("", maBN, ngayKhamTu, ngayKhamDen, tinhTrang, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchAppointments(
            @RequestParam(required = false) String maBS,
            @RequestParam(required = false) String maBN,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKhamTu,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKhamDen,
            @RequestParam(required = false, defaultValue = "DA_DAT") String tinhTrang,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(appointmentsQueryService.searchAppointments(maBS, maBN, ngayKhamTu, ngayKhamDen, tinhTrang, page, size));
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<MonthlyPatientStatisticDTO>> patientStatisticByMonth(@RequestParam int year) {
        List<MonthlyPatientStatisticDTO> stats = appointmentsQueryService.countPatientsByMonth(year);
        return ResponseEntity.ok(stats);
    }
}

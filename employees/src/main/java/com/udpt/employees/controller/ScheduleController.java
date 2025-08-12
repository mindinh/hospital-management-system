package com.udpt.employees.controller;


import com.udpt.employees.service.ISchedulesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/schedules", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ScheduleController {

    private ISchedulesService schedulesService;
    public ScheduleController(ISchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @GetMapping("/{maNV}")
    public ResponseEntity<?> getDoctorSchedules(@PathVariable String maNV) {

        return ResponseEntity.ok(schedulesService.getDoctorSchedule(maNV));
    }

}

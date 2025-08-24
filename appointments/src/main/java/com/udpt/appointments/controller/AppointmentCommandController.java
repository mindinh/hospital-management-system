package com.udpt.appointments.controller;


import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.dto.ResponseDto;
import com.udpt.appointments.service.IAppointmentsCommandService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppointmentCommandController {

    private IAppointmentsCommandService appointmentsCommandService;
    public AppointmentCommandController(IAppointmentsCommandService appointmentsService) {
        this.appointmentsCommandService = appointmentsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentCommand command) {
        appointmentsCommandService.createAppointment(command);

        return ResponseEntity.ok(new ResponseDto("201", "Appointment booked successfully"));
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody CreateAppointmentCommand command, Authentication authentication) {
        appointmentsCommandService.bookAppointment(command, authentication);

        return ResponseEntity.ok(new ResponseDto("201", "Appointment booked successfully"));
    }

    @PutMapping("/checkin/{id}")
    public ResponseEntity<?> checkinAppointment(@PathVariable String id) {
        boolean isSuccess = appointmentsCommandService.checkinAppointment(id);
        if (isSuccess) {
            return ResponseEntity.ok(new ResponseDto("200", "Thanh toán lịch khám thành công. Đã có thể khám"));
        }
        return ResponseEntity.ok(new ResponseDto("500", "Lỗi"));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable String id) {
        boolean isSuccess = appointmentsCommandService.cancelAppointment(id);
        if (isSuccess) {
            return ResponseEntity.ok(new ResponseDto("204", "Hủy lịch khám thành công"));
        }
        return ResponseEntity.ok(new ResponseDto("500", "Lỗi"));
    }

}

package com.udpt.employees.controller;


import com.udpt.employees.dto.ResponseDto;
import com.udpt.employees.request.DoctorInsertRequest;
import com.udpt.employees.request.EmployeeInsertRequest;
import com.udpt.employees.request.ScheduleInsertRequest;
import com.udpt.employees.service.IEmployeesService;
import com.udpt.employees.service.ISchedulesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private IEmployeesService employeesService;

    public EmployeeController(IEmployeesService employeesService) {
        this.employeesService = employeesService;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeInsertRequest request) {
        employeesService.createEmployee(request);

        return ResponseEntity.ok("Employee created successfully");
    }

    @PostMapping("/doctor/create")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorInsertRequest request) {
        employeesService.createDoctor(request);

        return ResponseEntity.ok("Doctor created successfully");
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable String id) {

        return ResponseEntity.ok(employeesService.getEmployeeDetails(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getDoctorDetails(@PathVariable String id) {

        return ResponseEntity.ok(employeesService.getDoctorDetails(id));
    }

    @PutMapping("/doctor/update-department/{empId}/{depId}")
    public ResponseEntity<?> updateDoctorDepartment(@PathVariable String empId, @PathVariable int depId) {
        boolean isSuccess = false;
        isSuccess = employeesService.updateDepartment(empId, depId);
        if (!isSuccess) {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Department update failed"));
        }
        else {
            return ResponseEntity.ok(new ResponseDto("200", "Employee updated successfully"));
        }
    }

    @PostMapping("/doctor/add-schedule")
    public ResponseEntity<?> addDoctorSchedule(@RequestBody ScheduleInsertRequest request) {
        employeesService.addSchedule(request);

        return ResponseEntity.ok(new ResponseDto("200", "Schedule added successfully"));
    }

    @PutMapping("/doctor/update-schedule/{id}")
    public ResponseEntity<?> updateDoctorSchedule(@PathVariable int id) {

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/doctor/remove-schedule/{id}")
    public ResponseEntity<?> removeDoctorSchedule(@PathVariable int id) {
        return ResponseEntity.ok("");
    }

}

package com.udpt.employees.controller;


import com.udpt.employees.request.DoctorInsertRequest;
import com.udpt.employees.request.EmployeeInsertRequest;
import com.udpt.employees.service.IEmployeesService;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable String id) {

        return ResponseEntity.ok(employeesService.getEmployeeDetails(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getDoctorDetails(@PathVariable String id) {

        return ResponseEntity.ok(employeesService.getDoctorDetails(id));
    }

}

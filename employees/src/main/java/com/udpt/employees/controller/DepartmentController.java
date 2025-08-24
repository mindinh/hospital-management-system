package com.udpt.employees.controller;


import com.udpt.employees.dto.ResponseDto;
import com.udpt.employees.request.DepartmentInsertRequest;
import com.udpt.employees.service.IDepartmentsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/departments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DepartmentController {

    private IDepartmentsService departmentsService;
    public DepartmentController(IDepartmentsService departmentsService) {
        this.departmentsService = departmentsService;

    }

    @GetMapping("/all-departments")
    public ResponseEntity<?> getAllDepartments() {

        return ResponseEntity.ok(departmentsService.getAllDepartment());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewDepartment(@RequestBody DepartmentInsertRequest request) {
        departmentsService.addNewDepartment(request);

        return ResponseEntity.ok(new ResponseDto("200", "Department added successfully"));
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getDepartmentDoctors(@PathVariable int id) {

        return ResponseEntity.ok(departmentsService.getAllEmployee(id));
    }

}

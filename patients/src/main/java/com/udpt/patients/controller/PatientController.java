package com.udpt.patients.controller;


import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.ResponseDto;
import com.udpt.patients.service.IPatientsService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PatientController {

    private IPatientsService patientsService;
    public PatientController(IPatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientDto patientDto) {
        patientsService.createPatient(patientDto);

        return ResponseEntity.ok(new ResponseDto("200", "Patient registered successfully"));
    }

    @GetMapping("/patient-details")
    public ResponseEntity<?> getPatientDetails(@RequestParam String mobileNo) {

        return ResponseEntity.ok(patientsService.getPatientDetails(mobileNo));
    }

}

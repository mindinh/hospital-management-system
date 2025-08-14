package com.udpt.patients.controller;


import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.RecordDto;
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

    @GetMapping("/patient-details/{id}")
    public ResponseEntity<?> getPatientDetailsById(@PathVariable String id) {

        return ResponseEntity.ok(patientsService.getPatientDetailsById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePatient(@Valid @RequestBody PatientDto patientDto) {
        patientsService.updatePatient(patientDto);

        return ResponseEntity.ok("Patient updated successfully")    ;
    }

    @PostMapping("/add-record")
    public ResponseEntity<?> addPatientRecord(@RequestParam String patientId, @RequestBody RecordDto recordDto) {
        boolean isSuccess = false;
        isSuccess = patientsService.addPatientRecord(patientId, recordDto);
        if (!isSuccess) {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Record not added successfully"));
        }
        else {
            return ResponseEntity.ok(new ResponseDto("200", "Record added successfully"));
        }

    }


}

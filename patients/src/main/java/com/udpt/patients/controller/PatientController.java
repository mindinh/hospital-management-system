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
@RequestMapping(value = "/api/v1/patients", produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @GetMapping("/details/me")
    public ResponseEntity<?> getPatientDetails(@RequestParam String mobileNo) {

        return ResponseEntity.ok(patientsService.getPatientDetails(mobileNo));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getPatientDetailsById(@PathVariable String id) {

        return ResponseEntity.ok(patientsService.getPatientDetailsById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPatients(
            @RequestParam(required = false) String maBN,
            @RequestParam(required = false) String hoTen,
            @RequestParam(required = false) String soDT,
            @RequestParam(required = false) String bhyt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(patientsService.searchPatients(maBN, hoTen, soDT, bhyt, page, size));
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

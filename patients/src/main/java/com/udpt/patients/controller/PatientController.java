package com.udpt.patients.controller;


import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.ResponseDto;
import com.udpt.patients.requests.PatientRegisterRequest;
import com.udpt.patients.requests.RecordInsertRequest;
import com.udpt.patients.service.IPatientsService;
import com.udpt.patients.service.IRecordsService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/patients", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PatientController {

    private IPatientsService patientsService;
    private IRecordsService recordsService;
    public PatientController(IPatientsService patientsService, IRecordsService recordsService) {
        this.patientsService = patientsService;
        this.recordsService = recordsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientRegisterRequest request) {
        patientsService.createPatient(request);

        return ResponseEntity.ok(new ResponseDto("201", "Patient registered successfully"));
    }

    @GetMapping("/details/me")
    public ResponseEntity<?> getPatientDetails(Authentication authentication) {

        return ResponseEntity.ok(patientsService.getPatientDetailsById(authentication.getName()));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getPatientDetailsById(@PathVariable String id) {

        return ResponseEntity.ok(patientsService.getPatientDetailsById(id));
    }

    @GetMapping("/details/my-records")
    public ResponseEntity<?> getPatientRecords(Authentication authentication) {

        return ResponseEntity.ok(recordsService.getAllPatientRecords(authentication.getName()));
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
    public ResponseEntity<?> addPatientRecord(@RequestBody RecordInsertRequest request) {
        boolean isSuccess = false;
        isSuccess = patientsService.addPatientRecord(request);
        if (!isSuccess) {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Record not added successfully"));
        }
        else {
            return ResponseEntity.ok(new ResponseDto("200", "Record added successfully"));
        }

    }


}

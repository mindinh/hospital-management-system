package com.udpt.medication.controller;


import com.udpt.medication.dto.ResponseDto;
import com.udpt.medication.request.CreatePrescriptionRequest;
import com.udpt.medication.service.IPrescriptionsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/prescriptions", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PrescriptionController {

    private IPrescriptionsService prescriptionsService;
    public PrescriptionController(IPrescriptionsService prescriptionsService) {
        this.prescriptionsService = prescriptionsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPrescription(@RequestBody CreatePrescriptionRequest request) {
        prescriptionsService.createPrescription(request);

        return ResponseEntity.ok(new ResponseDto("201", "Prescription created successfully"));
    }

    @GetMapping()
    public ResponseEntity<?> getAllPrescriptions(@RequestParam String maBenhNhan) {

        return ResponseEntity.ok(prescriptionsService.getPrescriptions(maBenhNhan));
    }


}

package com.udpt.patients.controller;


import com.udpt.patients.service.IRecordsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/records", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecordController {

    private IRecordsService recordsService;
    public RecordController(IRecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @GetMapping("/all-records")
    public ResponseEntity<?> getAllRecords() {
        return ResponseEntity.ok(recordsService.getAllRecords());
    }


}

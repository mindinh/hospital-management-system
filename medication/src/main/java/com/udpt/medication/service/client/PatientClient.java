package com.udpt.medication.service.client;

import com.udpt.medication.response.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients")
public interface PatientClient {

    @GetMapping("api/v1/patient-details/{id}")
    public PatientResponse getPatientDetailsById(@PathVariable String id);
}

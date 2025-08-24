package com.udpt.appointments.service.client;

import com.udpt.appointments.response.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients")
public interface PatientClient {

    @GetMapping("api/v1/patients/details/{id}")
    public PatientResponse getPatientDetailsById(@PathVariable String id);

    @GetMapping("api/v1/patients/details/me")
    public PatientResponse getPatientDetails();
}

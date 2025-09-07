package com.udpt.medication.service.client;

import com.udpt.medication.response.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees", fallback = DoctorFallback.class)
public interface DoctorClient {

    @GetMapping("api/v1/employees/doctor/{id}")
    public DoctorResponse getDoctorDetails(@PathVariable String id);

}

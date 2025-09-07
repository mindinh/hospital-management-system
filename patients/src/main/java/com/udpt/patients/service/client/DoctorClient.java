package com.udpt.patients.service.client;

import com.udpt.patients.response.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees", fallback = DoctorFallback.class)
public interface DoctorClient {

    @GetMapping("api/v1/employees/doctor/{id}")
    public DoctorResponse getDoctorDetails(@PathVariable String id);

}

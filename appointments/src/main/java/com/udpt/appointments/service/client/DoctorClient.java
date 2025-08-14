package com.udpt.appointments.service.client;

import com.udpt.appointments.response.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees")
public interface DoctorClient {

    @GetMapping("api/v1/doctor/{id}")
    public DoctorResponse getDoctorDetails(@PathVariable String id);

}

package com.udpt.patients.service.client;

import com.udpt.patients.response.DoctorResponse;
import org.springframework.stereotype.Component;

@Component
public class DoctorFallback implements DoctorClient{

    @Override
    public DoctorResponse getDoctorDetails(String id) {
        return null;
    }
}

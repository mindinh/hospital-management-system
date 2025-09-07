package com.udpt.medication.service.client;

import com.udpt.medication.response.DoctorResponse;
import org.springframework.stereotype.Component;

@Component
public class DoctorFallback implements DoctorClient{

    @Override
    public DoctorResponse getDoctorDetails(String id) {
        return null;
    }
}

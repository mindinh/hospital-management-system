package com.udpt.appointments.service.client;

import com.udpt.appointments.response.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientFallback implements PatientClient{

    @Override
    public PatientResponse getPatientDetailsById(String id) {
        return null;
    }

    @Override
    public PatientResponse getPatientDetails() {
        return null;
    }
}

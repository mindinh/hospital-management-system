package com.udpt.medication.service.client;

import com.udpt.medication.response.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientFallback implements PatientClient{

    @Override
    public PatientResponse getPatientDetailsById(String id) {
        return null;
    }

}

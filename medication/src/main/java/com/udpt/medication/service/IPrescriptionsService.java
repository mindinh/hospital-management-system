package com.udpt.medication.service;

import com.udpt.medication.dto.PrescriptionDto;
import com.udpt.medication.request.CreatePrescriptionRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPrescriptionsService {

    @Transactional
    void createPrescription(CreatePrescriptionRequest request);

    List<PrescriptionDto> getPrescriptions(String patientId);

    boolean completePrescriptionRetrieval(String prescriptionId);
    boolean completePrescriptionDelivery(String prescriptionId);
}

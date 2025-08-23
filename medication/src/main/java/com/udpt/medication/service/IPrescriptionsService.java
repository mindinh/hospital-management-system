package com.udpt.medication.service;

import com.udpt.medication.dto.PrescriptionDto;
import com.udpt.medication.request.CreatePrescriptionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface IPrescriptionsService {

    @Transactional
    void createPrescription(CreatePrescriptionRequest request);

    List<PrescriptionDto> getPrescriptions(String patientId);
    Page<PrescriptionDto> searchPrescriptions(String doctorId, String patientId, LocalDate prescribedDate, int page, int size);

    boolean completePrescriptionRetrieval(String prescriptionId);
    boolean completePrescriptionDelivery(String prescriptionId);
}

package com.udpt.medication.service;

import com.udpt.medication.dto.MonthlyPrescriptionStatisticDto;
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
    PrescriptionDto getPrescriptionDetails(String prescriptionId);
    Page<PrescriptionDto> searchPrescriptions(String doctorId, String patientId, LocalDate fromDate, LocalDate toDate, String status, int page, int size);

    boolean completePrescriptionRetrieval(String prescriptionId);
    boolean completePrescriptionDelivery(String prescriptionId);
    List<MonthlyPrescriptionStatisticDto> countPrescriptionsByMonth(int year);
}

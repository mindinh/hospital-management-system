package com.udpt.medication.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.medication.dto.PrescriptionDetailDto;
import com.udpt.medication.dto.PrescriptionDto;
import com.udpt.medication.dto.PrescriptionFlatDto;
import com.udpt.medication.entity.MedicationEntity;
import com.udpt.medication.entity.PrescriptionDetailEntity;
import com.udpt.medication.entity.PrescriptionEntity;
import com.udpt.medication.entity.Status;
import com.udpt.medication.exception.ResourceNotFoundException;
import com.udpt.medication.repository.MedicationsRepository;
import com.udpt.medication.repository.PrescriptionDetailsRepository;
import com.udpt.medication.repository.PrescriptionsRepository;
import com.udpt.medication.request.CreatePrescriptionRequest;
import com.udpt.medication.response.DoctorResponse;
import com.udpt.medication.response.PatientResponse;
import com.udpt.medication.service.IPrescriptionsService;
import com.udpt.medication.service.client.DoctorClient;
import com.udpt.medication.service.client.PatientClient;
import com.udpt.medication.utils.IdGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrescriptionsServiceImpl implements IPrescriptionsService {

    @PersistenceContext
    private EntityManager em;

    private MedicationsRepository medicationsRepository;
    private PrescriptionsRepository prescriptionsRepository;
    private PrescriptionDetailsRepository prescriptionDetailsRepository;
    private DoctorClient doctorClient;
    private PatientClient patientClient;


    @Override
    public void createPrescription(CreatePrescriptionRequest request) {
        DoctorResponse doctorResponse = doctorClient.getDoctorDetails(request.maBacSi());
        if (doctorResponse == null) {
            throw new ResourceNotFoundException("Bac Si", "Ma Bac Si", request.maBacSi());
        }
        PatientResponse patientResponse = patientClient.getPatientDetailsById(request.maBenhNhan());
        if (patientResponse == null) {
            throw new ResourceNotFoundException("Benh Nhan", "Ma Benh Nhan", request.maBenhNhan());
        }

        try {
            PrescriptionEntity prescription = new PrescriptionEntity();
            prescription.setId(IdGenerator.generateCode("P"));
            prescription.setDoctorId(request.maBacSi());
            prescription.setPatientId(request.maBenhNhan());
            prescription.setNotes(request.ghiChu());
            prescription.setStatus(Status.DOI_LAY_THUOC);
            prescription.setCreatedAt(LocalDateTime.now());
            prescription.setCreatedBy("medication-service");

            PrescriptionEntity finalPrescription = prescriptionsRepository.save(prescription);


            List<PrescriptionDetailEntity> prescriptionDetails = request.prescriptionDetailList().stream().map(
                    dto -> {
                       PrescriptionDetailEntity prescriptionDetail = new PrescriptionDetailEntity();
                       prescriptionDetail.setMedName(dto.getTenThuoc());
                       prescriptionDetail.setQuantity(dto.getSoLuong());
                       prescriptionDetail.setIndication(dto.getChiDinh());
                       prescriptionDetail.setPrescription(finalPrescription);
                       prescriptionDetail.setMedication(em.getReference(MedicationEntity.class, dto.getMaThuoc()));
                       return prescriptionDetail;
                    }
            ).toList();

            prescriptionDetailsRepository.saveAll(prescriptionDetails);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<PrescriptionDto> getPrescriptions(String patientId) {
        PatientResponse patientResponse = patientClient.getPatientDetailsById(patientId);
        if (patientResponse == null) {
            throw new ResourceNotFoundException("Benh Nhan", "Ma Benh Nhan", patientId);
        }

        List<PrescriptionFlatDto> rows = prescriptionsRepository.findPrescriptionsWithDetails(patientId);

        Map<String, List<PrescriptionFlatDto>> grouped = rows.stream()
                .collect(Collectors.groupingBy(PrescriptionFlatDto::maDonThuoc));

        return grouped.values().stream()
                .map(list -> {
                    PrescriptionFlatDto first = list.get(0);
                    List<PrescriptionDetailDto> details = list.stream()
                            .filter(r -> r.maChiTiet() != null)
                            .map(r -> new PrescriptionDetailDto(
                                    r.maThuoc(),
                                    r.tenThuoc(),
                                    r.soLuong(),
                                    r.chiDinh()
                            ))
                            .toList();
                    return new PrescriptionDto(
                            first.maDonThuoc(),
                            first.maBacSi(),
                            first.maBenhNhan(),
                            first.ghiChu(),
                            details
                    );
                })
                .toList();
    }

}

package com.udpt.medication.service.impl;

import com.udpt.medication.dto.MedicineDto;
import com.udpt.medication.entity.MedicationEntity;
import com.udpt.medication.exception.ResourceNotFoundException;
import com.udpt.medication.repository.MedicationsRepository;
import com.udpt.medication.request.CreateMedicineRequest;
import com.udpt.medication.service.IMedicationsService;
import com.udpt.medication.utils.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicationsServiceImpl implements IMedicationsService {

    private MedicationsRepository medicationsRepository;

    @Override
    public void createMedicine(CreateMedicineRequest request) {
        Optional<MedicationEntity> optionalMedication = medicationsRepository.findByMedicationRegisterNo(request.soDangKy());
        if (optionalMedication.isPresent()) {
            throw new RuntimeException("Medicine already exists");
        }

        MedicationEntity medication = new MedicationEntity();
        medication.setId(IdGenerator.generateMedicineCode());
        medication.setMedicationRegisterNo(request.soDangKy());
        medication.setMedicationName(request.tenThuoc());
        medication.setMedicationDescription(request.moTaThuoc());
        medication.setTreatsFor(request.dieuTri());
        medication.setMedicationType(request.loaiThuoc());
        medication.setQuantity(request.soLuong());

        medication.setCreatedAt(LocalDateTime.now());
        medication.setCreatedBy("medication-service");
        medicationsRepository.save(medication);

    }

    @Override
    public MedicineDto getMedicineDetails(String registerNo) {
        MedicationEntity medicationEntity = medicationsRepository.findByMedicationRegisterNo(registerNo).orElseThrow(
                () -> new ResourceNotFoundException("Thuoc", "So Dang Ky", registerNo)
        );
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setTenThuoc(medicationEntity.getMedicationName());
        medicineDto.setMoTaThuoc(medicationEntity.getMedicationDescription());
        medicineDto.setDieuTri(medicationEntity.getTreatsFor());
        medicineDto.setLoaiThuoc(medicationEntity.getMedicationType());

        return medicineDto;
    }
}

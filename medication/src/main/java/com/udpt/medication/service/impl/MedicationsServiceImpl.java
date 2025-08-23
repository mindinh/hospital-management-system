package com.udpt.medication.service.impl;

import com.udpt.medication.dto.MedicineDto;
import com.udpt.medication.entity.MedicationEntity;
import com.udpt.medication.entity.Status;
import com.udpt.medication.exception.ResourceNotFoundException;
import com.udpt.medication.repository.MedicationsRepository;
import com.udpt.medication.request.CreateMedicineRequest;
import com.udpt.medication.service.IMedicationsService;
import com.udpt.medication.utils.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        medication.setId(IdGenerator.generateCode("MD"));
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

    @Override
    public List<MedicineDto> getMedicinesByName(String name) {
        return medicationsRepository.findTop5ByMedicationNameContaining(name).stream().map(
                med -> {
                    MedicineDto medicineDto = new MedicineDto();
                    medicineDto.setMaThuoc(med.getId());
                    medicineDto.setTenThuoc(med.getMedicationName());
                    medicineDto.setMoTaThuoc(med.getMedicationDescription());
                    medicineDto.setDieuTri(med.getTreatsFor());
                    medicineDto.setLoaiThuoc(med.getMedicationType());

                    return medicineDto;
                }
        ).toList();
    }

    @Override
    public boolean updateMedicine(String medicineId, MedicineDto medicineDto) {
        MedicationEntity medicationEntity = medicationsRepository.findById(medicineId).orElseThrow(
                () -> new ResourceNotFoundException("Thuoc", "So Dang Ky", medicineId)
        );

        if (medicineDto.getTenThuoc() != null) {
            medicationEntity.setMedicationName(medicineDto.getTenThuoc());
        }
        if (medicineDto.getMoTaThuoc() != null) {
            medicationEntity.setMedicationDescription(medicineDto.getMoTaThuoc());
        }
        if (medicineDto.getDieuTri() != null) {
            medicationEntity.setTreatsFor(medicineDto.getDieuTri());
        }
        if (medicineDto.getLoaiThuoc() != null) {
            medicationEntity.setMedicationType(medicineDto.getLoaiThuoc());
        }
        medicationsRepository.save(medicationEntity);
        return true;
    }

    @Override
    public boolean deleteMedicine(String medicineId) {
        MedicationEntity medicationEntity = medicationsRepository.findById(medicineId).orElseThrow(
                () -> new ResourceNotFoundException("Thuoc", "So Dang Ky", medicineId)
        );

        medicationEntity.setStatus(Status.DELETED);
        medicationsRepository.save(medicationEntity);
        return true;
    }
}

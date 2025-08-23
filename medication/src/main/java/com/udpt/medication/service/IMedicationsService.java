package com.udpt.medication.service;

import com.udpt.medication.dto.MedicineDto;
import com.udpt.medication.request.CreateMedicineRequest;

import java.util.List;

public interface IMedicationsService {
    void createMedicine(CreateMedicineRequest request);
    MedicineDto getMedicineDetails(String registerNo);
    List<MedicineDto> getMedicinesByName(String name);
    boolean updateMedicine(String medicineId, MedicineDto medicineDto);
    boolean deleteMedicine(String medicineId);
}

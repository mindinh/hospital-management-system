package com.udpt.medication.service;

import com.udpt.medication.dto.MedicineDto;
import com.udpt.medication.request.CreateMedicineRequest;

public interface IMedicationsService {
    void createMedicine(CreateMedicineRequest request);
    MedicineDto getMedicineDetails(String registerNo);
    boolean updateMedicine(String medicineId, MedicineDto medicineDto);
    boolean deleteMedicine(String medicineId);
}

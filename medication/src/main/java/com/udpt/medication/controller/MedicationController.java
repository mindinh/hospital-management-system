package com.udpt.medication.controller;

import com.udpt.medication.dto.MedicineDto;
import com.udpt.medication.dto.ResponseDto;
import com.udpt.medication.request.CreateMedicineRequest;
import com.udpt.medication.service.IMedicationsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/medications", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MedicationController {

    private IMedicationsService medicationsService;
    public MedicationController(IMedicationsService medicationsService) {
        this.medicationsService = medicationsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewMedicine(@RequestBody CreateMedicineRequest request) {
        medicationsService.createMedicine(request);

        return ResponseEntity.ok(new ResponseDto("201", "Medicine created successfully"));
    }

    @GetMapping("/details")
    public ResponseEntity<?> getMedicineDetails(@RequestParam String registerNo) {

        return ResponseEntity.ok(medicationsService.getMedicineDetails(registerNo));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable String id, @RequestBody MedicineDto medicineDto) {
        boolean isSuccess = medicationsService.updateMedicine(id, medicineDto);
        if (isSuccess) {
            return ResponseEntity.ok(new ResponseDto("200", "Cập nhật thuốc thành công"));
        }
        return ResponseEntity.ok(new ResponseDto("400", "Yêu cầu cập nhật lỗi"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMedicine(@RequestParam String maThuoc) {
        boolean isSuccess = medicationsService.deleteMedicine(maThuoc);
        if (isSuccess) {
            return ResponseEntity.ok(new ResponseDto("200", "Đã xóa thuốc"));
        }
        return ResponseEntity.ok(new ResponseDto("400", "Yêu cầu xóa lỗi"));
    }

}

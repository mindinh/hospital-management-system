package com.udpt.medication.event.events;

import com.udpt.medication.dto.PrescriptionDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionReadyEvent {
    private String tenBacSi;
    private String tenBenhNhan;
    private String emailBenhNhan;
    private String ngayCap;
    private List<PrescriptionDetailDto> prescriptionDetails;
}

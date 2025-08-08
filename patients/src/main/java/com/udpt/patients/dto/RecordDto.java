package com.udpt.patients.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDto {

    private LocalDate visitDate;
    private String visitDepartment;
    private String doctorDiagnosis;
    private String patientSymptoms;
    private String doctorNotes;

}

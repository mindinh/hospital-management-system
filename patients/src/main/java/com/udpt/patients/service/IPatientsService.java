package com.udpt.patients.service;

import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.RecordDto;

import java.util.List;

public interface IPatientsService {
    void createPatient(PatientDto patientDto);
    PatientDto getPatientDetails(String mobileNo);
    List<RecordDto> getPatientRecords(String patientId);
    boolean addPatientRecord(String patientId, RecordDto recordDto);
}

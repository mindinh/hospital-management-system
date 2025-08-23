package com.udpt.patients.service;

import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.RecordDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPatientsService {
    void createPatient(PatientDto patientDto);
    Page<PatientDto> searchPatients(String code, String fullName, String mobileNo, String insuranceNo, int page, int size);
    PatientDto getPatientDetails(String mobileNo);
    PatientDto getPatientDetailsById(String id);
    List<RecordDto> getPatientRecords(String patientId);
    void updatePatient(PatientDto patientDto);
    boolean addPatientRecord(String patientId, RecordDto recordDto);
}

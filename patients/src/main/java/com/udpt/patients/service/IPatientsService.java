package com.udpt.patients.service;

import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.RecordDto;
import com.udpt.patients.requests.PatientRegisterRequest;
import com.udpt.patients.requests.RecordInsertRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPatientsService {
    void createPatient(PatientRegisterRequest request);
    Page<PatientDto> searchPatients(String code, String fullName, String mobileNo, String insuranceNo, int page, int size);
    PatientDto getPatientDetails(String mobileNo);
    PatientDto getPatientDetailsById(String id);
    List<RecordDto> getPatientRecords(String patientId);
    void updatePatient(PatientDto patientDto);
    boolean addPatientRecord(RecordInsertRequest recordInsertRequest);
}

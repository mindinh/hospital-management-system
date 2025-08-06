package com.udpt.patients.service;

import com.udpt.patients.dto.PatientDto;

public interface IPatientsService {
    void createPatient(PatientDto patientDto);
    PatientDto getPatientDetails(String mobileNo);
}

package com.udpt.patients.mapper;


import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.entity.Gender;
import com.udpt.patients.entity.PatientEntity;

public class PatientMapper {
    private PatientMapper() {}

    public static PatientDto mapToPatientDto(PatientEntity patientEntity, PatientDto patientDto) {
        patientDto.setFullname(patientEntity.getPatientFullname());
        patientDto.setGender(String.valueOf(patientEntity.getGender()));
        patientDto.setDateOfBirth(patientEntity.getPatientDOB());

        return patientDto;
    }

    public static PatientEntity mapToPatientEntity(PatientDto patientDto, PatientEntity patientEntity) {
        patientEntity.setPatientFullname(patientDto.getFullname());
        patientEntity.setPatientDOB(patientDto.getDateOfBirth());
        patientEntity.setGender(Gender.valueOf(patientDto.getGender()));


        return patientEntity;
    }


}

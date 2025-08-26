package com.udpt.patients.mapper;


import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.entity.Gender;
import com.udpt.patients.entity.PatientEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientMapper {
    private PatientMapper() {}

    public static PatientDto mapToPatientDto(PatientEntity patientEntity, PatientDto patientDto) {
        patientDto.setMaBenhNhan(patientEntity.getPatientId());
        patientDto.setSoDienThoai(patientEntity.getPatientMobileNo());
        patientDto.setEmailBenhNhan(patientEntity.getPatientEmail());
        patientDto.setHoTen(patientEntity.getPatientFullname());
        patientDto.setGioiTinh(String.valueOf(patientEntity.getGender()));
        patientDto.setNgaySinh(String.valueOf(patientEntity.getPatientDOB()));

        return patientDto;
    }

    public static PatientEntity mapToPatientEntity(PatientDto patientDto, PatientEntity patientEntity) {
        patientEntity.setPatientMobileNo(patientDto.getSoDienThoai());
        patientEntity.setPatientFullname(patientDto.getHoTen());
        patientEntity.setGender(Gender.valueOf(patientDto.getGioiTinh()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(patientDto.getNgaySinh(), formatter);
        patientEntity.setPatientDOB(dob);


        return patientEntity;
    }


}

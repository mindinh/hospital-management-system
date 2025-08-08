package com.udpt.patients.mapper;

import com.udpt.patients.dto.RecordDto;
import com.udpt.patients.entity.RecordEntity;

public class RecordMapper {
    private RecordMapper() {}

    public static RecordDto mapToRecordDto(RecordEntity recordEntity, RecordDto recordDto) {
        recordDto.setVisitDate(recordEntity.getVisitDate());
        recordDto.setVisitDepartment(recordEntity.getDepartmnet());
        recordDto.setPatientSymptoms(recordEntity.getSymptoms());
        recordDto.setDoctorDiagnosis(recordEntity.getDiagnosis());
        recordDto.setDoctorNotes(recordEntity.getNote());

        return recordDto;
    }

    public static RecordEntity mapToRecordEntity(RecordDto recordDto, RecordEntity recordEntity) {
        recordEntity.setDepartmnet(recordDto.getVisitDepartment());
        recordEntity.setSymptoms(recordDto.getPatientSymptoms());
        recordEntity.setDiagnosis(recordDto.getDoctorDiagnosis());
        recordEntity.setNote(recordDto.getDoctorNotes());

        return recordEntity;
    }
}

package com.udpt.patients.mapper;

import com.udpt.patients.dto.RecordDto;
import com.udpt.patients.entity.RecordEntity;

public class RecordMapper {
    private RecordMapper() {}

    public static RecordDto mapToRecordDto(RecordEntity recordEntity, RecordDto recordDto) {
        recordDto.setMaHoSo(recordEntity.getRecordId());
        recordDto.setNgayKham(recordEntity.getVisitDate());
        recordDto.setTrieuChung(recordEntity.getSymptoms());
        recordDto.setChanDoan(recordEntity.getDiagnosis());
        recordDto.setGhiChu(recordEntity.getNote());

        return recordDto;
    }

    public static RecordEntity mapToRecordEntity(RecordDto recordDto, RecordEntity recordEntity) {
        recordEntity.setSymptoms(recordDto.getTrieuChung());
        recordEntity.setDiagnosis(recordDto.getChanDoan());
        recordEntity.setNote(recordDto.getGhiChu());

        return recordEntity;
    }
}

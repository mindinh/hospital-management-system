package com.udpt.patients.service;

import com.udpt.patients.dto.RecordDto;

import java.util.List;

public interface IRecordsService {
    List<RecordDto> getAllRecords();
}

package com.udpt.patients.service.impl;

import com.udpt.patients.dto.RecordDto;
import com.udpt.patients.mapper.RecordMapper;
import com.udpt.patients.repository.RecordsRepository;
import com.udpt.patients.service.IRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecordsServiceImpl implements IRecordsService {

    private RecordsRepository recordsRepository;

    @Override
    public List<RecordDto> getAllRecords() {

        return recordsRepository.findAll().stream().map(
                record -> {
                    return RecordMapper.mapToRecordDto(record, new RecordDto());
                }
        ).toList();
    }
}

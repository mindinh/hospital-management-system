package com.udpt.patients.service.impl;

import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.RecordDto;
import com.udpt.patients.entity.PatientEntity;
import com.udpt.patients.entity.RecordEntity;
import com.udpt.patients.exception.PatientAlreadyExistException;
import com.udpt.patients.exception.ResourceNotFoundException;
import com.udpt.patients.mapper.PatientMapper;
import com.udpt.patients.mapper.RecordMapper;
import com.udpt.patients.repository.PatientsRepository;
import com.udpt.patients.repository.RecordsRepository;
import com.udpt.patients.service.IPatientsService;
import com.udpt.patients.utils.PatientIdGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientsServiceImpl implements IPatientsService {

    private PatientsRepository patientsRepository;
    private RecordsRepository recordsRepository;
    public PatientsServiceImpl(PatientsRepository patientsRepository, RecordsRepository recordsRepository) {
        this.patientsRepository = patientsRepository;
        this.recordsRepository = recordsRepository;
    }

    @Override
    public void createPatient(PatientDto patientDto) {
        Optional<PatientEntity> optionalPatientEntity = patientsRepository.findByPatientMobileNo(patientDto.getSoDienThoai());

        if (optionalPatientEntity.isPresent()) {
            throw new PatientAlreadyExistException(patientDto.getSoDienThoai());
        }
        PatientEntity patientEntity = PatientMapper.mapToPatientEntity(patientDto, new PatientEntity());

        patientEntity.setPatientId(patientDto.getMaBenhNhan());

        patientEntity.setCreatedAt(LocalDateTime.now());
        patientEntity.setCreatedBy("patient-service");
        patientsRepository.save(patientEntity);

    }

    @Override
    public PatientDto getPatientDetails(String mobileNo) {
        PatientEntity patientEntity = patientsRepository.findByPatientMobileNo(mobileNo).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "Mobile Number", mobileNo)
        );
        return PatientMapper.mapToPatientDto(patientEntity, new PatientDto());
    }

    @Override
    public PatientDto getPatientDetailsById(String id) {
        PatientEntity patientEntity = patientsRepository.findByPatientId(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "Patient Id", id)
        );
        return PatientMapper.mapToPatientDto(patientEntity, new PatientDto());
    }

    @Override
    public void updatePatient(PatientDto patientDto) {
        PatientEntity patientEntity = patientsRepository.findByPatientMobileNo(patientDto.getSoDienThoai()).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "Mobile Number", patientDto.getSoDienThoai())
        );

        PatientMapper.mapToPatientEntity(patientDto, patientEntity);
        patientsRepository.save(patientEntity);

    }

    @Override
    public List<RecordDto> getPatientRecords(String patientId) {
        List<RecordEntity> patientRecords = recordsRepository.findByPatientId(patientId);
        if (patientRecords.isEmpty()) {
            throw new ResourceNotFoundException("Patient Records", "Patient ID", patientId);
        }

        return patientRecords.stream().map(
                recordEntity -> RecordMapper.mapToRecordDto(recordEntity, new RecordDto())
        ).toList();
    }

    @Override
    public boolean addPatientRecord(String patientId, RecordDto recordDto) {
        PatientEntity patientEntity = patientsRepository.findByPatientId(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "Patient ID", patientId)
        );

        RecordEntity recordEntity = RecordMapper.mapToRecordEntity(recordDto, new RecordEntity());
        recordEntity.setPatient(patientEntity);
        recordEntity.setVisitDate(recordDto.getNgayKham());

        recordsRepository.save(recordEntity);

        return true;
    }
}

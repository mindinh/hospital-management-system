package com.udpt.patients.service.impl;

import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.entity.PatientEntity;
import com.udpt.patients.exception.PatientAlreadyExistException;
import com.udpt.patients.exception.ResourceNotFoundException;
import com.udpt.patients.mapper.PatientMapper;
import com.udpt.patients.repository.PatientsRepository;
import com.udpt.patients.service.IPatientsService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PatientsServiceImpl implements IPatientsService {

    private PatientsRepository patientsRepository;
    public PatientsServiceImpl(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @Override
    public void createPatient(PatientDto patientDto) {
        Optional<PatientEntity> optionalPatientEntity = patientsRepository.findByPatientMobileNo(patientDto.getMobileNo());

        if (optionalPatientEntity.isPresent()) {
            throw new PatientAlreadyExistException(patientDto.getMobileNo());
        }
        PatientEntity patientEntity = PatientMapper.mapToPatientEntity(patientDto, new PatientEntity());

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
}

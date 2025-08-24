package com.udpt.patients.service.impl;

import com.udpt.patients.dto.PatientDto;
import com.udpt.patients.dto.RecordDto;
import com.udpt.patients.entity.Gender;
import com.udpt.patients.entity.PatientEntity;
import com.udpt.patients.entity.RecordEntity;
import com.udpt.patients.exception.PatientAlreadyExistException;
import com.udpt.patients.exception.ResourceNotFoundException;
import com.udpt.patients.mapper.PatientMapper;
import com.udpt.patients.mapper.RecordMapper;
import com.udpt.patients.repository.PatientsRepository;
import com.udpt.patients.repository.RecordsRepository;
import com.udpt.patients.requests.PatientRegisterRequest;
import com.udpt.patients.requests.RecordInsertRequest;
import com.udpt.patients.response.DoctorResponse;
import com.udpt.patients.service.IPatientsService;
import com.udpt.patients.service.client.DoctorClient;
import com.udpt.patients.utils.IdGenerator;
import com.udpt.patients.utils.PatientSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PatientsServiceImpl implements IPatientsService {

    private PatientsRepository patientsRepository;
    private RecordsRepository recordsRepository;
    private DoctorClient doctorClient;
    public PatientsServiceImpl(PatientsRepository patientsRepository, RecordsRepository recordsRepository, DoctorClient doctorClient) {
        this.patientsRepository = patientsRepository;
        this.recordsRepository = recordsRepository;
        this.doctorClient = doctorClient;
    }

    @Override
    public void createPatient(PatientRegisterRequest request) {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Optional<PatientEntity> optionalPatientEntity = patientsRepository.findByPatientMobileNo(request.getSoDienThoai());

        if (optionalPatientEntity.isPresent()) {
            throw new PatientAlreadyExistException(request.getSoDienThoai());
        }
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setPatientId(request.getMaBenhNhan());
        patientEntity.setPatientFullname(request.getHoTen());
        patientEntity.setPatientMobileNo(request.getSoDienThoai());
        patientEntity.setPatientDOB(LocalDate.parse(request.getNgaySinh(), formatter));
        patientEntity.setGender(Gender.valueOf(request.getGioiTinh()));

        if (request.getDiaChi() != null) {
            patientEntity.setPatientAddress(request.getDiaChi());
        }

        if (request.getBhyt() != null) {
            patientEntity.setMedicalInsuranceNumber(request.getBhyt());
        }
        patientEntity.setCreatedAt(LocalDateTime.now());
        patientEntity.setCreatedBy("patient-service");

        patientsRepository.save(patientEntity);

    }

    @Override
    public PatientDto getPatientDetails(String mobileNo) {
        PatientEntity patientEntity = patientsRepository.findByPatientMobileNo(mobileNo).orElseThrow(
                () -> new ResourceNotFoundException("Benh Nhan", "So Dien Thoai", mobileNo)
        );
        return PatientMapper.mapToPatientDto(patientEntity, new PatientDto());
    }

    @Override
    public PatientDto getPatientDetailsById(String id) {
        PatientEntity patientEntity = patientsRepository.findByPatientId(id).orElseThrow(
                () -> new ResourceNotFoundException("Benh Nhan", "Ma Benh Nhan", id)
        );
        return PatientMapper.mapToPatientDto(patientEntity, new PatientDto());
    }

    @Override
    public Page<PatientDto> searchPatients(String code, String fullName, String mobileNo, String insuranceNo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("patientId").descending());
        Page<PatientEntity> patients =  patientsRepository.findAll(
                PatientSpecification.filter(code, fullName, mobileNo, insuranceNo),
                pageable
        );

        return patients.map(
                p -> new PatientDto(
                        p.getPatientId(),
                        p.getPatientMobileNo(),
                        p.getPatientFullname(),
                        p.getPatientDOB().toString(),
                        p.getGender().toString()
                )
        );
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
        List<RecordEntity> patientRecords = recordsRepository.findByPatient_PatientId(patientId);
        if (patientRecords.isEmpty()) {
            throw new ResourceNotFoundException("Patient Records", "Patient ID", patientId);
        }

        return patientRecords.stream().map(
                recordEntity -> RecordMapper.mapToRecordDto(recordEntity, new RecordDto())
        ).toList();
    }

    @Override
    public boolean addPatientRecord(RecordInsertRequest request) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        DoctorResponse doctorResponse = doctorClient.getDoctorDetails(request.maBacSi());
        if (doctorResponse == null) {
            throw new ResourceNotFoundException("Bac Si", "Ma Bac Si", request.maBacSi());
        }

        PatientEntity patientEntity = patientsRepository.findByPatientId(request.maBenhNhan()).orElseThrow(
                () -> new ResourceNotFoundException("Benh Nhan", "Ma Benh Nhan", request.maBenhNhan())
        );



        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setRecordId(IdGenerator.generateCode("R"));
        recordEntity.setPatient(patientEntity);
        recordEntity.setVisitDate(LocalDateTime.parse(request.ngayKham(), formatter));
        recordEntity.setSymptoms(request.trieuChung());
        recordEntity.setDiagnosis(request.chanDoan());
        recordEntity.setNote(request.ghiChu());
        recordEntity.setDoctorId(request.maBacSi());


        recordEntity.setCreatedAt(LocalDateTime.now());
        recordEntity.setCreatedBy("patients-service");

        recordsRepository.save(recordEntity);

        return true;
    }
}

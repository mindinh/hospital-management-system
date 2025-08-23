package com.udpt.patients.repository;

import com.udpt.patients.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PatientsRepository extends JpaRepository<PatientEntity, String>, JpaSpecificationExecutor<PatientEntity> {
    Optional<PatientEntity> findByPatientId(String patientId);
    Optional<PatientEntity> findByPatientMobileNo(String mobileNo);
    List<PatientEntity> findAll();
}

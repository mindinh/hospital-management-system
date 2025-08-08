package com.udpt.patients.repository;

import com.udpt.patients.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientsRepository extends JpaRepository<PatientEntity, String> {
    Optional<PatientEntity> findByPatientId(String patientId);
    Optional<PatientEntity> findByPatientMobileNo(String mobileNo);
}

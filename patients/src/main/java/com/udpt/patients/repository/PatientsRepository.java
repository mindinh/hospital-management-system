package com.udpt.patients.repository;

import com.udpt.patients.entity.PatientEntity;
import com.udpt.patients.entity.PatientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientsRepository extends JpaRepository<PatientEntity, PatientId> {
    Optional<PatientEntity> findByPatientMobileNo(String mobileNo);
}

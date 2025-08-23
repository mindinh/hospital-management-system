package com.udpt.patients.repository;

import com.udpt.patients.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsRepository extends JpaRepository<RecordEntity, Integer> {
    List<RecordEntity> findByPatient_PatientId(String patientId);
}

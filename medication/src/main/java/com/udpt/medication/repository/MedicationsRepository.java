package com.udpt.medication.repository;

import com.udpt.medication.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationsRepository extends JpaRepository<MedicationEntity, String> {
    Optional<MedicationEntity> findByMedicationRegisterNo(String registerNo);
}

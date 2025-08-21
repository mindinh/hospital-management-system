package com.udpt.medication.repository;

import com.udpt.medication.entity.PrescriptionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDetailsRepository extends JpaRepository<PrescriptionDetailEntity, String> {
}

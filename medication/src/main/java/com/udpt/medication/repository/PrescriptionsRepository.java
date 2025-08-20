package com.udpt.medication.repository;

import com.udpt.medication.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsRepository extends JpaRepository<PrescriptionEntity, String> {
}

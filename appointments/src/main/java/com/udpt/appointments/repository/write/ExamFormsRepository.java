package com.udpt.appointments.repository.write;

import com.udpt.appointments.entity.write.ExaminationFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamFormsRepository extends JpaRepository<ExaminationFormEntity, String> {
    Optional<ExaminationFormEntity> findByFormId(String id);
}

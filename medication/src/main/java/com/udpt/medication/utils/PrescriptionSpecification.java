package com.udpt.medication.utils;

import com.udpt.medication.entity.PrescriptionEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PrescriptionSpecification {
    public static Specification<PrescriptionEntity> filter(String doctorId, String patientId, LocalDate prescriptionDate) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (doctorId != null && !doctorId.isEmpty()) {
                predicates.add(cb.like(root.get("doctorId"), "%" + doctorId + "%"));
            }
            if (patientId != null && !patientId.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("patientId")), "%" + patientId + "%"));
            }
            if (prescriptionDate != null) {
                predicates.add(cb.equal(root.get("prescriptionDate"), prescriptionDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

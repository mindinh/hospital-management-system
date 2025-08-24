package com.udpt.medication.utils;

import com.udpt.medication.entity.PrescriptionEntity;
import com.udpt.medication.entity.Status;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PrescriptionSpecification {
    public static Specification<PrescriptionEntity> filter(String doctorId, String patientId, LocalDate fromDate, LocalDate toDate, Status status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (doctorId != null && !doctorId.isEmpty()) {
                predicates.add(cb.like(root.get("doctorId"), "%" + doctorId + "%"));
            }
            if (patientId != null && !patientId.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("patientId")), "%" + patientId + "%"));
            }
            if (fromDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("prescriptionDate"), fromDate));
            }
            if (toDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("prescriptionDate"), toDate));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), String.valueOf(status)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

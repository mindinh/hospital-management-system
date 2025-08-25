package com.udpt.appointments.utils;

import com.udpt.appointments.entity.read.AppointmentViewEntity;
import com.udpt.appointments.entity.Status;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AppointmentSpecification {
    public static Specification<AppointmentViewEntity> filter(String doctorId, String patientId, LocalDate fromDate, LocalDate toDate, Status status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (doctorId != null && !doctorId.isEmpty()) {
                predicates.add(cb.like(root.get("doctorId"), "%" + doctorId + "%"));
            }
            if (patientId != null && !patientId.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("patientId")), "%" + patientId.toLowerCase() + "%"));
            }
            if (fromDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("appointmentDate"), fromDate));
            }
            if (toDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("appointmentDate"), toDate));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), String.valueOf(status)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

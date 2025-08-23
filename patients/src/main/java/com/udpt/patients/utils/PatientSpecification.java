package com.udpt.patients.utils;

import com.udpt.patients.entity.PatientEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class PatientSpecification {
    public static Specification<PatientEntity> filter(String code, String fullName, String phoneNumber, String insuranceNo) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (code != null && !code.isEmpty()) {
                predicates.add(cb.like(root.get("patientId"), "%" + code + "%"));
            }
            if (fullName != null && !fullName.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("patientFullname")), "%" + fullName.toLowerCase() + "%"));
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                predicates.add(cb.like(root.get("patientMobileNo"), "%" + phoneNumber + "%"));
            }
            if (insuranceNo != null && !insuranceNo.isEmpty()) {
                predicates.add(cb.like(root.get(""), "%" + phoneNumber + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package com.udpt.employees.repository;

import com.udpt.employees.entity.DegreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreesRepository extends JpaRepository<DegreeEntity, Integer> {
}

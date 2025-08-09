package com.udpt.employees.repository;

import com.udpt.employees.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeeEntity, String> {
    Optional<EmployeeEntity> findBySoDTNV(String soDT);
    Optional<EmployeeEntity> findByMaChungChi(String maChungChi);
    Optional<EmployeeEntity> findByMaNV(String maBacSi);

}

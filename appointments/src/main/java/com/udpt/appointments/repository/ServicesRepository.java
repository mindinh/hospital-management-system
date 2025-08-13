package com.udpt.appointments.repository;

import com.udpt.appointments.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<ServiceEntity, Integer> {
    Optional<ServiceEntity> findByTenDichVu(String ten);
}

package com.udpt.appointments.repository.write;

import com.udpt.appointments.entity.write.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<ServiceEntity, Integer> {
    Optional<ServiceEntity> findByTenDichVu(String ten);
}

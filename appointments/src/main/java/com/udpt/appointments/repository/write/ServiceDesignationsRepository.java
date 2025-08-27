package com.udpt.appointments.repository.write;

import com.udpt.appointments.entity.write.ServiceDesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceDesignationsRepository extends JpaRepository<ServiceDesignationEntity, String> {
    Optional<ServiceDesignationEntity> findById(String id);
}

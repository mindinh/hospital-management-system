package com.udpt.appointments.repository.read;

import com.udpt.appointments.entity.read.AppointmentViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsReadRepository extends JpaRepository<AppointmentViewEntity, String> {
    Optional<AppointmentViewEntity> findByAppointmentId(String appointmentId);
    List<AppointmentViewEntity> findAll();

    Page<AppointmentViewEntity> findAll(Specification<AppointmentViewEntity> filter, Pageable pageable);
}

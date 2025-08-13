package com.udpt.appointments.repository;

import com.udpt.appointments.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<AppointmentEntity, String> {
    List<AppointmentEntity> findByMaBenhNhanAndTrangThai(String maBn, String tt);
    List<AppointmentEntity> findByMaBacSiAndTrangThai(String maBs, String tt);
}

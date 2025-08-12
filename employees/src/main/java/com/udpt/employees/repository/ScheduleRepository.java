package com.udpt.employees.repository;

import com.udpt.employees.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    List<ScheduleEntity> findByBacsi_MaNV(String maNV);
    List<ScheduleEntity> findByBacsi_MaNVAndNgayLamViecBetween(String maBacSi, LocalDate batDau, LocalDate ketThuc);
}

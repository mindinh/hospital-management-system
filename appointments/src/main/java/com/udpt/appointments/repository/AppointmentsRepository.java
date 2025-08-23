package com.udpt.appointments.repository;

import com.udpt.appointments.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsRepository extends JpaRepository<AppointmentEntity, String>, JpaSpecificationExecutor<AppointmentEntity> {
    Optional<AppointmentEntity> findByAppointmentId(String appointmentId);
    List<AppointmentEntity> findAll();
    List<AppointmentEntity> findByMaBenhNhanAndTrangThai(String maBn, String tt);
    List<AppointmentEntity> findByMaBacSiAndTrangThai(String maBs, String tt);

    @Query("SELECT COUNT(DISTINCT a.maBenhNhan) " +
            "FROM AppointmentEntity a " +
            "WHERE a.maBacSi = :maBacSi " +
            "AND a.ngayKham BETWEEN :startDate AND :endDate " +
            "AND a.trangThai != 'DA_HUY'")
    int countPatientsByDoctorAndDateRange(
            @Param("maBacSi") String maBacSi,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT COUNT(DISTINCT a.maBenhNhan) " +
            "FROM AppointmentEntity a " +
            "WHERE a.ngayKham BETWEEN :startDate AND :endDate " +
            "AND a.trangThai != 'DA_HUY'")
    int countPatientsByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}

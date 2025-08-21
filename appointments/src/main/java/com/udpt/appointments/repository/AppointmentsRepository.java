package com.udpt.appointments.repository;

import com.udpt.appointments.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<AppointmentEntity, String> {
    List<AppointmentEntity> findByMaBenhNhanAndTrangThai(String maBn, String tt);
    List<AppointmentEntity> findByMaBacSiAndTrangThai(String maBs, String tt);

    @Query("SELECT COUNT(DISTINCT a.maBenhNhan) " +
            "FROM AppointmentEntity a " +
            "WHERE a.maBacSi = :maBacSi " +
            "AND a.ngayKham BETWEEN :startDate AND :endDate " +
            "AND a.trangThai IN ('DA_DAT', 'DA_THANH_TOAN')")
    int countPatientsByDoctorAndDateRange(
            @Param("maBacSi") String maBacSi,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}

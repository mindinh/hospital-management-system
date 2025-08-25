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
    List<AppointmentEntity> findByPatientIdAndStatus(String maBn, String tt);
    List<AppointmentEntity> findByDoctorIdAndStatus(String maBs, String tt);

    @Query(value = "SELECT MONTH(l.ngay_kham) AS month, " +
            "COUNT(DISTINCT l.ma_benh_nhan) AS patients " +
            "FROM lichkham l " +
            "WHERE YEAR(l.ngay_kham) = :year " +
            "AND l.tinh_trang != 'DA_HUY' " +
            "GROUP BY MONTH(l.ngay_kham) " +
            "ORDER BY month",
            nativeQuery = true)
    List<Object[]> countPatientsByMonth(@Param("year") int year);



}

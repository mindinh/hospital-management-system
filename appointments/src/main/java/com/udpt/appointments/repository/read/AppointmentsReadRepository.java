package com.udpt.appointments.repository.read;

import com.udpt.appointments.entity.read.AppointmentViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsReadRepository extends JpaRepository<AppointmentViewEntity, String> {
    Optional<AppointmentViewEntity> findByAppointmentId(String appointmentId);
    List<AppointmentViewEntity> findAll();

    Page<AppointmentViewEntity> findAll(Specification<AppointmentViewEntity> filter, Pageable pageable);
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

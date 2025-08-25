package com.udpt.appointments.repository.write;

import com.udpt.appointments.entity.write.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsWriteRepository extends JpaRepository<AppointmentEntity, String>, JpaSpecificationExecutor<AppointmentEntity> {
    Optional<AppointmentEntity> findByAppointmentId(String appointmentId);
    List<AppointmentEntity> findAll();
    List<AppointmentEntity> findByPatientIdAndStatus(String maBn, String tt);
    List<AppointmentEntity> findByDoctorIdAndStatus(String maBs, String tt);

    @Query("SELECT COUNT(DISTINCT a.patientId) " +
            "FROM AppointmentEntity a " +
            "WHERE a.doctorId = :maBacSi " +
            "AND a.appointmentDate BETWEEN :startDate AND :endDate " +
            "AND a.status != 'DA_HUY'")
    int countPatientsByDoctorAndDateRange(
            @Param("maBacSi") String maBacSi,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT COUNT(DISTINCT a.patientId) " +
            "FROM AppointmentEntity a " +
            "WHERE a.appointmentDate BETWEEN :startDate AND :endDate " +
            "AND a.status != 'DA_HUY'")
    int countPatientsByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}

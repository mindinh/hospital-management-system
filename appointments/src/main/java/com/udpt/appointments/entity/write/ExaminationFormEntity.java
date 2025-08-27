package com.udpt.appointments.entity.write;


import com.udpt.appointments.entity.BaseEntity;
import com.udpt.appointments.entity.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "phieukham")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ExaminationFormEntity extends BaseEntity {
    @Id
    @Column(name = "ma_phieu")
    private String formId;

    @Column(name = "ma_bac_si")
    private String doctorId;

    @Column(name = "ma_benh_nhan")
    private String patientId;

    @Column(name = "ngay_kham")
    private LocalDate appointmentDate;

    @Column(name = "gio_kham")
    private LocalTime appointmentTime;

    @Column(name = "so_thu_tu")
    private String number;

    @Column(name = "so_phong")
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "tinh_trang")
    private Status status;

    @OneToOne
    @JoinColumn(name = "ma_lich_kham", referencedColumnName = "ma_lich_kham")
    private AppointmentEntity appointment;

    @OneToMany(mappedBy = "form")
    private List<ServiceDesignationEntity> serviceDesignationList;
}

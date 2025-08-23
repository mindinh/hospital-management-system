package com.udpt.appointments.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "phieukham")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ExaminationFormEntity extends BaseEntity {
    @Id
    @Column(name = "ma_phieu")
    private String formId;

    @Column(name = "ten_bac_si")
    private String doctorName;

    @Column(name = "ten_benh_nhan")
    private String patientName;

    @Column(name = "ngay_kham")
    private LocalDate appointmentDate;

    @Column(name = "gio_kham")
    private LocalTime appointmentTime;

    @Column(name = "so_thu_tu")
    private int number;

    @Column(name = "so_phong")
    private String roomNumber;

    @OneToOne
    @JoinColumn(name = "ma_lich_kham", referencedColumnName = "ma_lich_kham")
    private AppointmentEntity appointment;
}

package com.udpt.appointments.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lichkham")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class AppointmentEntity extends BaseEntity {
    @Id
    @Column(name = "ma_lich_kham")
    private String appointmentId;

    @Column(name = "ma_bac_si")
    private String doctorId;

    @Column(name = "ma_benh_nhan")
    private String patientId;

    @Column(name = "ngay_kham")
    private LocalDate appointmentDate;

    @Column(name = "gio_kham")
    private LocalTime appointmentTime;

    @Column(name = "ghi_chu")
    private String appointmentNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "tinh_trang")
    private Status status;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private ExaminationFormEntity examinationForm;

}

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
public class AppointmentEntity {
    @Id
    @Column(name = "ma_lich_kham")
    private String maLich;

    @Column(name = "ma_bac_si")
    private String maBacSi;

    @Column(name = "ma_benh_nhan")
    private String maBenhNhan;

    @Column(name = "ngay_kham")
    private LocalDate ngayKham;

    @Column(name = "gio_kham")
    private LocalTime gioKham;

    @Enumerated(EnumType.STRING)
    private Status trangThai;

    @OneToOne(mappedBy = "lich", cascade = CascadeType.ALL)
    private ExaminationFormEntity examinationForm;

}

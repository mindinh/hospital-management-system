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
    private String maLichKham;

    @Column(name = "ma_bac_si")
    private String maBacSi;

    @Column(name = "ma_benh_nhan")
    private String maBenhNhan;

    @Column(name = "ngay_kham")
    private LocalDate ngayKham;

    @Column(name = "gio_kham")
    private LocalTime gioKham;

    @Column(name = "ghi_chu")
    private String ghiChuKham;

    @Enumerated(EnumType.STRING)
    @Column(name = "tinh_trang")
    private Status trangThai;

    @OneToOne(mappedBy = "lich", cascade = CascadeType.ALL)
    private ExaminationFormEntity examinationForm;

}

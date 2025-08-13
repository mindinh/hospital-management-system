package com.udpt.appointments.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "phieukham")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ExaminationFormEntity {
    @Id
    @Column(name = "ma_phieu")
    private String maPhieu;

    @Column(name = "ten_bac_si")
    private String tenBacSi;

    @Column(name = "ten_benh_nhan")
    private String tenBenhNhan;

    @Column(name = "ngay_kham")
    private LocalDate ngayKham;

    @Column(name = "gio_kham")
    private LocalTime gioKham;

    @Column(name = "so_thu_tu")
    private int soThuTu;

    @Column(name = "so_phong")
    private String soPhong;

    @OneToOne
    @JoinColumn(name = "ma_lich_kham", referencedColumnName = "maLichKham")
    private AppointmentEntity lich;
}

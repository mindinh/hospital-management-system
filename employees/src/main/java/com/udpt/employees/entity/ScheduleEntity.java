package com.udpt.employees.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lichlamviec")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_lich")
    private int id;

    @Column(name = "ngay_lam_viec")
    private LocalDate ngayLamViec;

    @Column(name = "gio_bat_dau")
    private LocalTime gioBatDau;

    @Column(name = "gio_ket_thuc")
    private LocalTime gioKetThuc;

    @Column(name = "phong")
    private String soPhong;

    @ManyToOne
    @JoinColumn(name = "ma_bac_si", referencedColumnName = "ma_nv", nullable = false)
    private EmployeeEntity bacsi;
}

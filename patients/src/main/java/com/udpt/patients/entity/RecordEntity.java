package com.udpt.patients.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "hoso_benhnhan")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class RecordEntity {

    @Id
    @Column(name = "ma_ho_so")
    private int id;

    @Column(name = "ma_benh_nhan")
    private String patientId;

    @Column(name = "ngay_kham")
    private LocalDate visitDate;

    @Column(name = "trieu_chung")
    private String symptoms;

    @Column(name = "chan_doan")
    private String diagnosis;

    @Column(name = "ma_bac_si")
    private String doctorId;

    @Column(name = "ghi_chu_dieu_tri")
    private String note;

    @ManyToOne
    @JoinColumn(name = "ma_benh_nhan", insertable = false, updatable = false)
    private PatientEntity patient;
}

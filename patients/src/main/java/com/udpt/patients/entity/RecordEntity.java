package com.udpt.patients.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hoso_benhnhan")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class RecordEntity extends BaseEntity {

    @Id
    @Column(name = "ma_ho_so")
    private String recordId;

    @Column(name = "ngay_kham")
    private LocalDateTime visitDate;

    @Column(name = "trieu_chung")
    private String symptoms;

    @Column(name = "chan_doan")
    private String diagnosis;

    @Column(name = "ma_bac_si")
    private String doctorId;

    @Column(name = "ghi_chu_dieu_tri")
    private String note;

    @ManyToOne
    @JoinColumn(name = "ma_benh_nhan")
    private PatientEntity patient;
}

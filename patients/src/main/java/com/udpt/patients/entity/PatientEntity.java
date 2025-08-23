package com.udpt.patients.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "benhnhan")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class PatientEntity extends BaseEntity {

    @Id
    @Column(name = "ma_benh_nhan")
    private String patientId;

    @Column(name = "so_dt_bn")
    private String patientMobileNo;

    @Column(name = "ho_ten_bn")
    private String patientFullname;

    @Column(name = "ngay_sinh_bn")
    private LocalDate patientDOB;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh_bn")
    private Gender gender;

    @Column(name = "dia_chi_bn")
    private String patientAddress;

    @Column(name = "so_bhyt_bn")
    private String medicalInsuranceNumber;

    @OneToMany(mappedBy = "patient")
    private List<RecordEntity> records;

}

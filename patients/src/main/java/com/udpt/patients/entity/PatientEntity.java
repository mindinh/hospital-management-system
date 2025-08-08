package com.udpt.patients.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patients")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class PatientEntity extends BaseEntity {

    @Id
    private String patientId;

    @Column(unique = true, nullable = false, name = "account_id", columnDefinition = "BINARY(16)")
    private UUID accountId;

    @Column(name = "patient_mobile_number")
    private String patientMobileNo;

    @Column(name = "patient_fullname")
    private String patientFullname;

    @Column(name = "patient_dob")
    private Date patientDOB;

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_gender")
    private Gender gender;

    @Column(name = "patient_address")
    private String patientAddress;

    @Column(name = "medical_insurance_number")
    private String medialInsuranceNumber;

    @OneToMany(mappedBy = "patient")
    private List<RecordEntity> records;

}

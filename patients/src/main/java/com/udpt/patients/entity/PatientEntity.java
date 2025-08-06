package com.udpt.patients.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "patients")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class PatientEntity extends BaseEntity {

    @EmbeddedId
    private PatientId id;

    @Column(name = "patient_mobile_number")
    private String patientMobileNo;

    @Column(name = "patient_fullname")
    private String patientFullname;

    @Column(name = "patient_dob")
    private Date patientDOB;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "patient_address")
    private String patientAddress;

    @Column(name = "medical_insurance_number")
    private String medialInsuranceNumber;

}

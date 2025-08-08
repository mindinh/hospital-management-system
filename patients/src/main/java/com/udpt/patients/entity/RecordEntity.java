package com.udpt.patients.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "records")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class RecordEntity {

    @Id
    private int id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "department")
    private String departmnet;

    @Column(name = "treament_notes")
    private String note;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;
}

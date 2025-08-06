package com.udpt.patients.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "records")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class RecordEntity {
    @Id
    private String patientId;

    @Id
    private int recordId;
}

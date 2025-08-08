package com.udpt.patients.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class RecordId implements Serializable {

    private String patient_id;

    @Column(name = "visit_date")
    private LocalDateTime visitDate;

}

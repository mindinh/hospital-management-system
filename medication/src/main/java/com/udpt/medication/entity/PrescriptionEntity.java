package com.udpt.medication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "donthuoc")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionEntity extends BaseEntity {

    @Id
    @Column(name = "ma_don_thuoc")
    private String id;

    @Column(name = "ma_bac_si")
    private String doctorId;

    @Column(name = "ma_benh_nhan")
    private String patientId;

    @Column(name = "ghi_chu")
    private String notes;

    @Column(name = "ngay_cap")
    private LocalDateTime prescriptionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "tinh_trang")
    private Status status;

    @OneToMany(mappedBy = "prescription")
    private List<PrescriptionDetailEntity> prescriptionDetails;

}

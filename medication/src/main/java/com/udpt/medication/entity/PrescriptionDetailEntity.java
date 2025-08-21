package com.udpt.medication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chitiet_donthuoc")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionDetailEntity {

    @Id
    @Column(name = "ma_chi_tiet")
    private int id;

    @Column(name = "ten_thuoc")
    private String medName;

    @Column(name = "so_luong")
    private int quantity;

    @Column(name = "chi_dinh")
    private String indication;

    @ManyToOne
    @JoinColumn(name = "ma_don_thuoc")
    private PrescriptionEntity prescription;

    @ManyToOne
    @JoinColumn(name = "ma_thuoc")
    private MedicationEntity medication;

}

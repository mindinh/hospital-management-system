package com.udpt.medication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "thuoc")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class MedicationEntity extends BaseEntity {
    @Id
    @Column(name = "ma_thuoc")
    private String id;

    @Column(name = "so_dk_thuoc")
    private String medicationRegisterNo;

    @Column(name = "ten_thuoc")
    private String medicationName;

    @Column(name = "mo_ta_thuoc")
    private String medicationDescription;

    @Column(name = "loai_thuoc")
    private String medicationType;

    @Column(name = "dieu_tri")
    private String treatsFor;

    @Column(name = "so_luong")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "tinh_trang")
    private Status status;

}

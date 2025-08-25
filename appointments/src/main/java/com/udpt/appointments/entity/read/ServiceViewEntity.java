package com.udpt.appointments.entity.read;

import com.udpt.appointments.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dichvu")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class ServiceViewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dich_vu")
    private int maDichVu;

    @Column(name = "ten_dich_vu")
    private String tenDichVu;

    @Column(name = "mo_ta_dich_vu")
    private String moTaDV;
}

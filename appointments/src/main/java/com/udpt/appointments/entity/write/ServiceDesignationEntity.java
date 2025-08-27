package com.udpt.appointments.entity.write;

import com.udpt.appointments.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chidinh_dichvu")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class ServiceDesignationEntity extends BaseEntity {
    @Id
    @Column(name = "ma_chi_dinh")
    private String id;

    @Column(name = "ten_dich_vu")
    private String serviceName;

    @Column(name = "mo_ta")
    private String description;

    @Column(name = "so_thu_tu")
    private String number;

    @Column(name = "so_phong")
    private String roomNo;

    @Column(name = "ket_qua")
    private String result;

    @ManyToOne
    @JoinColumn(name = "ma_phieu_kham")
    private ExaminationFormEntity form;

    @ManyToOne
    @JoinColumn(name = "ma_dich_vu")
    private ServiceEntity service;
}

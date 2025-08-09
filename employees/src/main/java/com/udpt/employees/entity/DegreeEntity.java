package com.udpt.employees.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bangcap")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DegreeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_bang_cap")
    private int id;

    @Column(name = "ten_bang_cap")
    private String tenBangCap;

    @Column(name = "ngay_cap")
    private LocalDate ngayCap;

    @Column(name = "don_vi_cap")
    private String donViCap;

    @ManyToOne
    @JoinColumn(name = "ma_nv", insertable = false , updatable = false)
    private EmployeeEntity nhanvien;

}

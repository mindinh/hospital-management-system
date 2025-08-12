package com.udpt.employees.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "khoa")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DepartmentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khoa")
    private int id;

    @Column(name = "ten_khoa")
    private String tenKhoa;

    @Column(name = "gioi_thieu_khoa")
    private String gioiThieu;

    @Column(name = "truong_khoa")
    private String truongKhoa;

    @OneToMany(mappedBy = "khoa")
    private List<EmployeeEntity> dsNhanVien;

}

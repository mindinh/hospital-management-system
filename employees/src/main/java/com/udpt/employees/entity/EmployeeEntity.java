package com.udpt.employees.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "nhanvien")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class EmployeeEntity extends BaseEntity {

    @Id
    @Column(name = "ma_nv")
    private String maNV;

    @Column(name = "ma_chung_chi")
    private String maChungChi;

    @Column(name = "so_dt_nv")
    private String soDTNV;

    @Column(name = "ho_ten_nv")
    private String hoTenNV;

    @Column(name = "ngay_sinh_nv")
    private LocalDate ngaySinhNV;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh_nv")
    private Gender gioiTinhNV;

    @Column(name = "dia_chi_nv")
    private String diaChiNV;

    @Enumerated(EnumType.STRING)
    @Column(name = "chuc_vu")
    private Role chucVu;

    @Column(name = "kinh_nghiem")
    private String kinhNghiem;

    @OneToMany(mappedBy = "nhanvien")
    private List<DegreeEntity> bangCap;

    @ManyToOne
    @JoinColumn(name = "ma_khoa")
    private DepartmentEntity khoa;

}

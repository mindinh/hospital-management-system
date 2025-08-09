package com.udpt.employees.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Experience {

    private String benhVien;
    private String chuyenKhoa;
    private String viTri;
    private int namBatDau;
    private int namKetThuc;


}

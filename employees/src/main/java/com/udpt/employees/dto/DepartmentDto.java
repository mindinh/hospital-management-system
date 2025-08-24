package com.udpt.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private int maKhoa;
    private String tenKhoa;
    private String gioiThieuKhoa;
    private String truongKhoa;
    private Long soBacSi;

    public DepartmentDto(int id, String khoa, String gioiThieuKhoa, String truongKhoa, Long soBacSi) {
        this.maKhoa = id;
        this.tenKhoa = khoa;
        this.gioiThieuKhoa = gioiThieuKhoa;
        this.truongKhoa = truongKhoa;
        this.soBacSi = soBacSi;
    }
}

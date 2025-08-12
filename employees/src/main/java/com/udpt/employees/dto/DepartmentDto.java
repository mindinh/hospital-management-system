package com.udpt.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DepartmentDto {
    private String khoa;
    private String gioiThieuKhoa;
    private String truongKhoa;
    private Long soBacSi;

    public DepartmentDto(String khoa, String gioiThieuKhoa, String truongKhoa, Long soBacSi) {
        this.khoa = khoa;
        this.gioiThieuKhoa = gioiThieuKhoa;
        this.truongKhoa = truongKhoa;
        this.soBacSi = soBacSi;
    }
}

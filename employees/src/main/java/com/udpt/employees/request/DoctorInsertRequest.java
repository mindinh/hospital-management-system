package com.udpt.employees.request;

import com.udpt.employees.entity.Experience;

import java.util.List;

public record DoctorInsertRequest(
        String maChungChi,
        String hoTen,
        String ngaySinh,
        String gioiTinh,
        String diaChi,
        List<Experience> kinhNghiem
) {
}

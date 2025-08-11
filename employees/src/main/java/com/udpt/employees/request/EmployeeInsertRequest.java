package com.udpt.employees.request;

public record EmployeeInsertRequest(
        String maNV,
        String chucVu,
        String soDT,
        String hoTen,
        String ngaySinh,
        String gioiTinh,
        String diaChi,
        String kinhNghiem
) {
}

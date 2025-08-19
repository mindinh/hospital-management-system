package com.udpt.medication.request;

public record CreateMedicineRequest(
        String soDangKy,
        String tenThuoc,
        String moTaThuoc,
        String loaiThuoc,
        String dieuTri,
        int soLuong
) {
}

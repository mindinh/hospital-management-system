package com.udpt.medication.dto;

public record PrescriptionFlatDto(
        String maDonThuoc,
        String maBacSi,
        String maBenhNhan,
        String ghiChu,
        String ngayCap,
        String tinhTrang,
        String maChiTiet,
        String maThuoc,
        String tenThuoc,
        Integer soLuong,
        String chiDinh
) {}

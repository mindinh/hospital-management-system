package com.udpt.notifications.dto;



public class PrescriptionDetailDto {
    private String maThuoc;
    private String tenThuoc;
    private int soLuong;
    private String chiDinh;

    public PrescriptionDetailDto() {

    }

    public PrescriptionDetailDto(String maThuoc, String tenThuoc, int soLuong, String chiDinh) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.soLuong = soLuong;
        this.chiDinh = chiDinh;
    }

    public String getChiDinh() {
        return chiDinh;
    }

    public void setChiDinh(String chiDinh) {
        this.chiDinh = chiDinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }
}

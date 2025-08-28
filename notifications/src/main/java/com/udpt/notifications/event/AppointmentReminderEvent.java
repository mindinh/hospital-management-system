package com.udpt.notifications.event;

import java.time.LocalDateTime;

public class AppointmentReminderEvent {
    private String maLichKham;
    private String tenBacSi;
    private String chuyenKhoa;
    private String tenBenhNhan;
    private String emailBenhNhan;
    private LocalDateTime ngayHen;

    public AppointmentReminderEvent() {

    }

    public AppointmentReminderEvent(
            String maLichKham,
            String tenBacSi,
            String khoa,
            String tenBenhNhan,
            String emailBenhNhan,
            LocalDateTime ngayHen
    ) {
        this.maLichKham = maLichKham;
        this.tenBacSi = tenBacSi;
        this.chuyenKhoa = khoa;
        this.tenBenhNhan = tenBenhNhan;
        this.emailBenhNhan = emailBenhNhan;
        this.ngayHen = ngayHen;
    }

    public String getMaLichKham() {
        return maLichKham;
    }

    public void setMaLichKham(String maLichKham) {
        this.maLichKham = maLichKham;
    }

    public String getTenBacSi() {
        return tenBacSi;
    }

    public void setTenBacSi(String tenBacSi) {
        this.tenBacSi = tenBacSi;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public LocalDateTime getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(LocalDateTime ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public String getEmailBenhNhan() {
        return emailBenhNhan;
    }

    public void setEmailBenhNhan(String emailBenhNhan) {
        this.emailBenhNhan = emailBenhNhan;
    }
}

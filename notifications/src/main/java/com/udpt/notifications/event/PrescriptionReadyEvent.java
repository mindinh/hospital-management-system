package com.udpt.notifications.event;

import com.udpt.notifications.dto.PrescriptionDetailDto;

import java.util.List;


public class PrescriptionReadyEvent {
    private String tenBacSi;
    private String tenBenhNhan;
    private String emailBenhNhan;
    private String ngayCap;
    private List<PrescriptionDetailDto> prescriptionDetails;

    public PrescriptionReadyEvent() {

    }

    public PrescriptionReadyEvent(String tenBacSi,  String tenBenhNhan, String emailBenhNhan, String ngayCap, List<PrescriptionDetailDto> prescriptionDetails) {
        this.tenBacSi = tenBacSi;
        this.tenBenhNhan = tenBenhNhan;
        this.emailBenhNhan = emailBenhNhan;
        this.ngayCap = ngayCap;
        this.prescriptionDetails = prescriptionDetails;
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

    public String getTenBacSi() {
        return tenBacSi;
    }

    public void setTenBacSi(String tenBacSi) {
        this.tenBacSi = tenBacSi;
    }

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }

    public List<PrescriptionDetailDto> getPrescriptionDetails() {
        return prescriptionDetails;
    }

    public void setPrescriptionDetails(List<PrescriptionDetailDto> prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
    }
}

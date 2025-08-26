package com.udpt.notifications.event;

public class OtpGeneratedEvent {
    private String email;
    private String otp;

    public OtpGeneratedEvent() {

    }

    public OtpGeneratedEvent(String email, String otp) {
        this.email = email;
        this.otp = otp;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

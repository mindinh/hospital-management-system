package com.udpt.accounts.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OtpGeneratedEvent {
    private String email;
    private String otp;
}

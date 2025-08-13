package com.udpt.appointments.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentInsertRequest(
        String maBacSi,
        String maBenhNhan,
        LocalDate ngayKham,
        LocalTime gioKham
) {
}

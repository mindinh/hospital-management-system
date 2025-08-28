package com.udpt.appointments.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AppointmentReminderEvent {
    private String maLichKham;
    private String tenBacSi;
    private String chuyenKhoa;
    private String tenBenhNhan;
    private String emailBenhNhan;
    private LocalDateTime ngayHen;

}

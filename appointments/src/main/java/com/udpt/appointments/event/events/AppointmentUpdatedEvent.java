package com.udpt.appointments.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AppointmentUpdatedEvent {
    private String appointmentId;
    private String status;
}

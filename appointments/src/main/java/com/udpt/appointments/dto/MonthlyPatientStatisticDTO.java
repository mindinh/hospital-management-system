package com.udpt.appointments.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class MonthlyPatientStatisticDTO {
    private int month;
    private int totalPatients;
}

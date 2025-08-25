package com.example.statistics.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class MonthlyPatientStatisticDto {
    private int month;
    private int totalPatients;
}


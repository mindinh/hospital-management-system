package com.example.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class MonthlyStatisticDto {
    private int month;
    private int totalPatients;
    private int totalPrescriptions;
}
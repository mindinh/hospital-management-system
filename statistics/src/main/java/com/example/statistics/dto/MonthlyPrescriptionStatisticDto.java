package com.example.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class MonthlyPrescriptionStatisticDto {
    private int month;
    private int totalPrescriptions;
}


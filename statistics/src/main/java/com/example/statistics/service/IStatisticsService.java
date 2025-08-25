package com.example.statistics.service;

import com.example.statistics.dto.MonthlyPatientStatisticDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IStatisticsService {
    List<MonthlyPatientStatisticDTO> getPatientStatistic(int year);
}

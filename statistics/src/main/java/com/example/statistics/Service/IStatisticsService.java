package com.example.statistics.Service;

import java.time.LocalDate;
import java.util.Map;

public interface IStatisticsService {
    Map<String,Object> getPatientStatistic(String maBacSi, LocalDate startDate, LocalDate endDate);
}

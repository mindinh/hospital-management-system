package com.example.statistics.service;

import java.time.LocalDate;
import java.util.Map;

public interface IStatisticsService {
    Map<String,Object> getPatientByDoctorStatistic(String maBacSi, LocalDate startDate, LocalDate endDate);
    Map<String,Object> getPatientStatistic(LocalDate startDate, LocalDate endDate);
}

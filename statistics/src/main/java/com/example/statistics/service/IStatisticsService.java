package com.example.statistics.service;

import com.example.statistics.dto.MonthlyStatisticDto;

import java.util.List;

public interface IStatisticsService {
    List<MonthlyStatisticDto> getAllStatistic(int year);
}

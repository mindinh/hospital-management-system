package com.example.statistics.controller;

import com.example.statistics.dto.MonthlyPatientStatisticDTO;
import com.example.statistics.service.IStatisticsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/statistics", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StatisticController {
    private IStatisticsService statisticsService;
    public StatisticController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getPatients(@RequestParam int year) {
        List<MonthlyPatientStatisticDTO> statistic = statisticsService.getPatientStatistic(year);
        return ResponseEntity.ok(statistic);
    }
}

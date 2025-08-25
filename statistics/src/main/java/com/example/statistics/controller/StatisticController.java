package com.example.statistics.controller;

import com.example.statistics.dto.MonthlyStatisticDto;
import com.example.statistics.service.IStatisticsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/statistics", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StatisticController {
    private IStatisticsService statisticsService;
    public StatisticController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MonthlyStatisticDto>> getAllStatistics(
            @RequestParam int year) {

        List<MonthlyStatisticDto> result = statisticsService.getAllStatistic(year);

        return ResponseEntity.ok(result);
    }
}

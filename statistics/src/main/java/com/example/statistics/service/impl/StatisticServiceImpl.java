package com.example.statistics.service.impl;

import com.example.statistics.service.IStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@Service
public class StatisticServiceImpl implements IStatisticsService {

    private RestTemplate restTemplate;
    public StatisticServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, Object> getPatientByDoctorStatistic(String maBacSi, LocalDate startDate, LocalDate endDate) {
        String url = String.format(
                "http://appointments/api/v1/appointments/statistic/doctor?maBacSi=%s&startDate=%s&endDate=%s",
                maBacSi, startDate, endDate
        );

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> getPatientStatistic(LocalDate startDate, LocalDate endDate) {
        String url = String.format(
                "http://appointments/api/v1/appointments/statistic?startDate=%s&endDate=%s",
                startDate, endDate
        );

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }
}

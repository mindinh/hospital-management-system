package com.example.statistics.service.impl;

import com.example.statistics.dto.MonthlyPatientStatisticDTO;
import com.example.statistics.service.IStatisticsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements IStatisticsService {

    private RestTemplate restTemplate;
    private HttpServletRequest request;
    public StatisticServiceImpl(RestTemplate restTemplate, HttpServletRequest request){
        this.restTemplate = restTemplate;
        this.request = request;
    }

    @Override
    public List<MonthlyPatientStatisticDTO> getPatientStatistic(int year) {
        String url = String.format(
                "http://appointments/api/v1/appointments/statistic?year=%d",
                year
        );

        // Lấy token trực tiếp từ request header
        String authHeader = request.getHeader("Authorization");

        HttpHeaders headers = new HttpHeaders();
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            headers.set("Authorization", authHeader);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MonthlyPatientStatisticDTO[]> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, MonthlyPatientStatisticDTO[].class);

        return Arrays.asList(response.getBody());
    }

}

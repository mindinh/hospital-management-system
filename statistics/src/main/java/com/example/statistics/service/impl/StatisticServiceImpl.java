package com.example.statistics.service.impl;

import com.example.statistics.dto.MonthlyStatisticDto;
import com.example.statistics.dto.MonthlyPatientStatisticDto;
import com.example.statistics.dto.MonthlyPrescriptionStatisticDto;
import com.example.statistics.service.IStatisticsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class StatisticServiceImpl implements IStatisticsService {

    private RestTemplate restTemplate;
    private HttpServletRequest request;
    public StatisticServiceImpl(RestTemplate restTemplate, HttpServletRequest request){
        this.restTemplate = restTemplate;
        this.request = request;
    }

    @Override
    public List<MonthlyStatisticDto> getAllStatistic(int year) {
        // Lấy token từ request header

        String authHeader = request.getHeader("Authorization");

        HttpHeaders headers = new HttpHeaders();
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            headers.set("Authorization", authHeader);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Lấy url 2 api thống kê
        String urlAppointments = String.format("http://appointments/api/v1/appointments/statistic?year=%d", year);
        String urlPrescriptions = String.format("http://medication/api/v1/prescriptions/statistic?year=%d", year);

        // 1. Call song song bằng CompletableFuture
        CompletableFuture<List<MonthlyPatientStatisticDto>> patientsFuture =
                CompletableFuture.supplyAsync(() -> {
                    ResponseEntity<MonthlyPatientStatisticDto[]> response =
                            restTemplate.exchange(urlAppointments, HttpMethod.GET, entity, MonthlyPatientStatisticDto[].class);
                    return Arrays.asList(response.getBody());
                });

        CompletableFuture<List<MonthlyPrescriptionStatisticDto>> prescriptionsFuture =
                CompletableFuture.supplyAsync(() -> {
                    ResponseEntity<MonthlyPrescriptionStatisticDto[]> response =
                            restTemplate.exchange(urlPrescriptions, HttpMethod.GET, entity, MonthlyPrescriptionStatisticDto[].class);
                    return Arrays.asList(response.getBody());
                });

        // 2. Chờ cả 2 xong
        CompletableFuture.allOf(patientsFuture, prescriptionsFuture).join();

        List<MonthlyPatientStatisticDto> patients = patientsFuture.join();
        List<MonthlyPrescriptionStatisticDto> prescriptions = prescriptionsFuture.join();

        // 3. Merge kết quả
        Map<Integer, MonthlyStatisticDto> resultMap = new HashMap<>();

        for (MonthlyPatientStatisticDto p : patients) {
            resultMap.putIfAbsent(p.getMonth(), new MonthlyStatisticDto(p.getMonth(), 0, 0));
            resultMap.get(p.getMonth()).setTotalPatients(p.getTotalPatients());
        }

        for (MonthlyPrescriptionStatisticDto m : prescriptions) {
            resultMap.putIfAbsent(m.getMonth(), new MonthlyStatisticDto(m.getMonth(), 0, 0));
            resultMap.get(m.getMonth()).setTotalPrescriptions(m.getTotalPrescriptions());
        }

        // 4. Trả ra list sorted theo month
        return resultMap.values().stream()
                .sorted(Comparator.comparingInt(MonthlyStatisticDto::getMonth))
                .toList();
    }


}

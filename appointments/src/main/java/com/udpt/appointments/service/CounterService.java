package com.udpt.appointments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CounterService {

    private StringRedisTemplate redisTemplate;
    public CounterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long getNextCounter(String roomNo) {
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String key = "counter:phong:" + roomNo + ":" + today;

        Boolean isNew = redisTemplate.opsForValue()
                .setIfAbsent(key, "99", Duration.ofDays(1));

        return redisTemplate.opsForValue().increment(key);
    }

}

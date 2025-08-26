package com.udpt.accounts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.accounts.config.RabbitMQConfig;
import com.udpt.accounts.entity.Role;
import com.udpt.accounts.event.events.OtpGeneratedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private final RedisTemplate<String, String> redisTemplate;
    private final SecureRandom secureRandom = new SecureRandom();
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OtpService(RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void generateOtp(String email) {
        int otp = 100000 + secureRandom.nextInt(900000);
        String key = "otp:" + email;

        redisTemplate.opsForValue().set(key, String.valueOf(otp), 5, TimeUnit.MINUTES);

        OtpGeneratedEvent event = new OtpGeneratedEvent(email, String.valueOf(otp));

        try {
            String json = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.OTP_EXCHANGE,
                    RabbitMQConfig.OTP_ROUTING_KEY,
                    json
            );

        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean verifyOtp(String email, String inputOtp) {
        String key = "otp:" + email;
        String cachedOtp = redisTemplate.opsForValue().get(key);

        if (cachedOtp != null && cachedOtp.equals(inputOtp)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}

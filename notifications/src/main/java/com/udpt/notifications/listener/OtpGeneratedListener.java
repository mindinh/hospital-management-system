package com.udpt.notifications.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.notifications.config.RabbitMQConfig;
import com.udpt.notifications.event.OtpGeneratedEvent;
import com.udpt.notifications.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OtpGeneratedListener {
    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    public OtpGeneratedListener(final EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.OTP_QUEUE)
    public void consumePrescription(String messageJson) throws JsonProcessingException {
        OtpGeneratedEvent event = objectMapper.readValue(messageJson, OtpGeneratedEvent.class);

        String subject = "Xác thực email";


        emailService.sendEmail(
                event.getEmail(),
                subject,
                "<h2>OTP: " + event.getOtp() + "</h2>"
        );
    }
}

package com.udpt.appointments.event.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.appointments.config.RabbitMQConfig;
import com.udpt.appointments.event.events.AppointmentCreatedEvent;
import com.udpt.appointments.event.events.AppointmentReminderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AppointmentEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Qualifier("stringRabbitTemplate")
    private RabbitTemplate stringRabbitTemplate;

    private final ObjectMapper objectMapper;

    public AppointmentEventPublisher(RabbitTemplate rabbitTemplate, RabbitTemplate stringRabbitTemplate , ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.stringRabbitTemplate = stringRabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishAppointmentCreated(AppointmentCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APPOINTMENT_EXCHANGE,
                RabbitMQConfig.APPOINTMENT_ROUTING_KEY,
                event
        );
    }

    public void sendReminderEvent(AppointmentReminderEvent event) {
        long delay = Duration.between(LocalDateTime.now(), event.getNgayHen().minusHours(24)).toMillis();

        if (delay < 0) {
            delay = 0;
        }

        long finalDelay = delay;

        try {
            String json = objectMapper.writeValueAsString(event);
            stringRabbitTemplate.convertAndSend(
                    RabbitMQConfig.REMINDER_EXCHANGE,
                    RabbitMQConfig.APPOINTMENT_REMINDER_ROUTING_KEY,
                    json,
                    message -> {
                        message.getMessageProperties().setHeader("x-delay", finalDelay);
                        return message;
                    }
            );
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}

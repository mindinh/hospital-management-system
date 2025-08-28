package com.udpt.notifications.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {
    public static final String OTP_QUEUE = "otp.queue";
    public static final String PRESCRIPTION_QUEUE = "prescription.queue";
    public static final String PRESCRIPTION_EXCHANGE = "prescription.exchange";
    public static final String PRESCRIPTION_ROUTING_KEY = "prescription.ready";
    public static final String REMINDER_EXCHANGE = "reminder.exchange";
    public static final String REMINDER_QUEUE = "reminder.queue";
    public static final String APPOINTMENT_REMINDER_ROUTING_KEY = "reminder.key";

}

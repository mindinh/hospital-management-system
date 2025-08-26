package com.udpt.patients.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String PATIENT_ACCOUNT_QUEUE = "patient.account.queue";
    public static final String ACCOUNT_EXCHANGE = "account.exchange";
    public static final String ACCOUNT_ROUTING_KEY_PATIENT = "account.created.patient";
}

package com.udpt.employees.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EMPLOYEE_ACCOUNT_QUEUE = "employee.account.queue";
    public static final String ACCOUNT_EXCHANGE = "account.exchange";
    public static final String ACCOUNT_ROUTING_KEY_EMPLOYEE = "account.created.employee";
}

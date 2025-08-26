package com.udpt.accounts.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String OTP_QUEUE = "otp.queue";
    public static final String OTP_EXCHANGE = "otp.exchange";
    public static final String OTP_ROUTING_KEY = "otp.created";
    public static final String PATIENT_ACCOUNT_QUEUE = "patient.account.queue";
    public static final String EMPLOYEE_ACCOUNT_QUEUE = "employee.account.queue";
    public static final String ACCOUNT_EXCHANGE = "account.exchange";
    public static final String ACCOUNT_ROUTING_KEY_PATIENT = "account.created.patient";
    public static final String ACCOUNT_ROUTING_KEY_EMPLOYEE = "account.created.employee";


//    @Bean
//    public Jackson2JsonMessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(messageConverter());
//        return template;
//    }
//
//    @Bean
//    public Queue accountCreatedQueue() {
//        return new Queue("account.created", false);
//    }
    @Bean
    public Queue otpQueue() {
        return new Queue(OTP_QUEUE, true);
    }

    @Bean
    public Queue patientAccountQueue() {
        return new Queue(PATIENT_ACCOUNT_QUEUE, true);
    }

    @Bean
    public Queue employeeAccountQueue() {
        return new Queue(EMPLOYEE_ACCOUNT_QUEUE, true);
    }

    @Bean
    public TopicExchange accountExchange() {
        return new TopicExchange(ACCOUNT_EXCHANGE);
    }

    @Bean
    public TopicExchange otpExchange() {
        return new TopicExchange(OTP_EXCHANGE);
    }

    @Bean
    public Binding otpBinding(Queue otpQueue, TopicExchange otpExchange) {
        return BindingBuilder
                .bind(otpQueue)
                .to(otpExchange)
                .with(OTP_ROUTING_KEY);
    }

    @Bean
    public Binding accountPatientBinding(Queue patientAccountQueue, TopicExchange accountExchange) {
        return BindingBuilder
                .bind(patientAccountQueue)
                .to(accountExchange)
                .with(ACCOUNT_ROUTING_KEY_PATIENT);
    }

    @Bean
    public Binding accountEmployeeBinding(Queue employeeAccountQueue, TopicExchange accountExchange) {
        return BindingBuilder
                .bind(employeeAccountQueue)
                .to(accountExchange)
                .with(ACCOUNT_ROUTING_KEY_EMPLOYEE);
    }

}

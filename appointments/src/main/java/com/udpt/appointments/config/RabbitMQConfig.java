package com.udpt.appointments.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfig {
    public static final String APPOINTMENT_CREATED_QUEUE = "appointment.created.queue";
    public static final String APPOINTMENT_UPDATED_QUEUE = "appointment.updated.queue";
    public static final String APPOINTMENT_EXCHANGE = "appointment.exchange";
    public static final String APPOINTMENT_ROUTING_KEY = "appointment.created";
    public static final String APPOINTMENT_UPDATED_ROUTING_KEY = "appointment.updated";
    public static final String REMINDER_EXCHANGE = "reminder.exchange";
    public static final String REMINDER_QUEUE = "reminder.queue";
    public static final String APPOINTMENT_REMINDER_ROUTING_KEY = "reminder.key";


    @Bean
    public CustomExchange reminderExchange() {
        return new CustomExchange(
                REMINDER_EXCHANGE,
                "x-delayed-message",
                true,
                false,
                Map.of("x-delayed-type", "direct")
        );
    }

    @Bean
    public Queue reminderQueue() {
        return new Queue(REMINDER_QUEUE, true);
    }

    @Bean
    public Binding binding(Queue reminderQueue, CustomExchange reminderExchange) {
        return BindingBuilder.bind(reminderQueue)
                .to(reminderExchange)
                .with(APPOINTMENT_REMINDER_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Queue appointmentCreatedQueue() {
        return new Queue(APPOINTMENT_CREATED_QUEUE, true);
    }

    @Bean
    public Queue appointmentUpdatedQueue() {
        return new Queue(APPOINTMENT_UPDATED_QUEUE, true);
    }

    @Bean
    public TopicExchange appointmentExchange() {
        return new TopicExchange(APPOINTMENT_EXCHANGE);
    }

    @Bean
    public Binding appointmentCreatedBinding(Queue appointmentCreatedQueue, TopicExchange appointmentExchange) {
        return BindingBuilder
                .bind(appointmentCreatedQueue)
                .to(appointmentExchange)
                .with(APPOINTMENT_ROUTING_KEY);
    }

    @Bean
    public Binding appointmentUpdatedBinding(Queue appointmentUpdatedQueue, TopicExchange appointmentExchange) {
        return BindingBuilder
                .bind(appointmentUpdatedQueue)
                .to(appointmentExchange)
                .with(APPOINTMENT_UPDATED_ROUTING_KEY);
    }
}

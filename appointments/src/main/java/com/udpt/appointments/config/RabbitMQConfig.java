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
    public static final String APPOINTMENT_QUEUE = "appointment.queue";
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
    public Queue appointmentQueue() {
        return new Queue(APPOINTMENT_QUEUE, true);
    }

    @Bean
    public TopicExchange appointmentExchange() {
        return new TopicExchange(APPOINTMENT_EXCHANGE);
    }

    @Bean
    public Binding appointmentBinding(Queue appointmentQueue, TopicExchange appointmentExchange) {
        return BindingBuilder
                .bind(appointmentQueue)
                .to(appointmentExchange)
                .with(APPOINTMENT_ROUTING_KEY);
    }

    @Bean
    public Binding appointmentUpdatedBinding(Queue appointmentQueue, TopicExchange appointmentExchange) {
        return BindingBuilder
                .bind(appointmentQueue)
                .to(appointmentExchange)
                .with(APPOINTMENT_UPDATED_ROUTING_KEY);
    }
}

package com.udpt.appointments.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String APPOINTMENT_QUEUE = "appointment.queue";
    public static final String APPOINTMENT_EXCHANGE = "appointment.exchange";
    public static final String APPOINTMENT_ROUTING_KEY = "appointment.created";
    public static final String APPOINTMENT_UPDATED_ROUTING_KEY = "appointment.updated";

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(producerJackson2MessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(producerJackson2MessageConverter());
        return factory;
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

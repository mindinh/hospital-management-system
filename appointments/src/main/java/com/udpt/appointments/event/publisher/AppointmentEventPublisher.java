package com.udpt.appointments.event.publisher;

import com.udpt.appointments.config.RabbitMQConfig;
import com.udpt.appointments.event.events.AppointmentCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppointmentEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public AppointmentEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAppointmentCreated(AppointmentCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APPOINTMENT_EXCHANGE,
                RabbitMQConfig.APPOINTMENT_ROUTING_KEY,
                event
        );
    }
}

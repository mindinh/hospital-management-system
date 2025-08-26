package com.udpt.medication.event.publisher;

import com.udpt.medication.config.RabbitMQConfig;
import com.udpt.medication.event.events.PrescriptionReadyEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public PrescriptionEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAppointmentCreated(PrescriptionReadyEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PRESCRIPTION_EXCHANGE,
                RabbitMQConfig.PRESCRIPTION_ROUTING_KEY,
                event
        );
    }
}

package com.udpt.accounts.event.publisher;

import com.udpt.accounts.event.events.OtpGeneratedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class OtpEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    public OtpEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOtpGenerated(OtpGeneratedEvent event) {
        rabbitTemplate.convertAndSend("otp.created", event);
    }
}

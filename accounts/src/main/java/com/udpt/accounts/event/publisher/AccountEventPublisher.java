package com.udpt.accounts.event.publisher;

import com.udpt.accounts.event.events.AccountCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventPublisher {

    private RabbitTemplate rabbitTemplate;
    public AccountEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAccountCreated(AccountCreatedEvent event) {
        rabbitTemplate.convertAndSend("account.created", event);
    }


}

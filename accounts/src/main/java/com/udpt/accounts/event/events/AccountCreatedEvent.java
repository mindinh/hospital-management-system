package com.udpt.accounts.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Data
@AllArgsConstructor @NoArgsConstructor
public class AccountCreatedEvent {
    private UUID accountId;
    private String emailAddr;
    private String mobileNo;
    private String accountType;

}

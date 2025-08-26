package com.udpt.employees.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private String emailAddr;

    private String name;
    private String mobileNo;
    private String dob;
    private String gender;
    private String accountType;

}

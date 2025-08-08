package com.udpt.patients.dto;


import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PatientDto {

    // For testing
    private UUID accountNo;
    // Replace with an account created event later

    private String mobileNo;

    private String fullName;

    private Date dateOfBirth;

    private String gender;

}

package com.udpt.patients.dto;


import lombok.Data;

import java.util.Date;

@Data
public class PatientDto {

    private String mobileNo;

    private String fullName;

    private Date dateOfBirth;

    private String gender;

}

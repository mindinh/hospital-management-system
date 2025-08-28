package com.udpt.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be a valid email address")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String soDienThoai;

    @Pattern(regexp = "^[a-zA-Z0-9@#$%^&+=!._-]{6,15}$", message = "Username invalid or too short")
    private String tenNguoiDung;

}

package com.udpt.auth.dto;

public record AuthenticationResponse(
        String usernam,
        String hoTen,
        String email,
        String accessToken,
        String refreshToken
) {}

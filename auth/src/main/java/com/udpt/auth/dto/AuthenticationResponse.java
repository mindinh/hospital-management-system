package com.udpt.auth.dto;

public record AuthenticationResponse(
        String username,
        String hoTen,
        String email,
        String accessToken,
        String refreshToken
) {}

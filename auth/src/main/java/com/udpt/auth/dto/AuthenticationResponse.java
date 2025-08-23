package com.udpt.auth.dto;

public record AuthenticationResponse(
        String id,
        String username,
        String hoTen,
        String email,
        String accessToken,
        String refreshToken
) {}

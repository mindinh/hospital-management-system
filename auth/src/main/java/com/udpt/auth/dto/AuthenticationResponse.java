package com.udpt.auth.dto;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {}

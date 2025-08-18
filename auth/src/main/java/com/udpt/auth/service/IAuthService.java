package com.udpt.auth.service;

import com.udpt.auth.dto.AuthenticationResponse;

public interface IAuthService {
    AuthenticationResponse login(String phone, String password);
    String refreshAccessToken(String refreshToken);
}

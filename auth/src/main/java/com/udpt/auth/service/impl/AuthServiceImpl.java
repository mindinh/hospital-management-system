package com.udpt.auth.service.impl;

import com.udpt.auth.dto.AuthenticationResponse;
import com.udpt.auth.entity.UserEntity;
import com.udpt.auth.repository.UserRepository;
import com.udpt.auth.service.IAuthService;
import com.udpt.auth.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    @Value("${jwt.key}")
    private String keyJwt;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository usersRepository;
    private final JwtHelper jwtHelper;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository usersRepository, JwtHelper jwtHelper) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public AuthenticationResponse login(String email, String password) {
        String accessToken = "", refreshToken = "", username = "", fullname = "";

        Optional<UserEntity> user = usersRepository.findByEmailAddress(email);
        if (user.isPresent()) {
            UserEntity u = user.get();

            if (passwordEncoder.matches(password, u.getPassword())) {
                username = u.getUsername();
                accessToken = jwtHelper.generateAccessToken(String.valueOf(u.getRole()));
                refreshToken = jwtHelper.generateRefreshToken(String.valueOf(u.getRole()));
            }

        }
        return new AuthenticationResponse(username, fullname, email, accessToken, refreshToken);
    }


    @Override
    public String refreshAccessToken(String refreshToken) {
        String accessToken = "";

        if (refreshToken != null && !jwtHelper.isTokenExpired(refreshToken)) {
            String role = jwtHelper.getDataToken(refreshToken);
            accessToken = jwtHelper.generateAccessToken(role);
        }

        return accessToken;
    }
}

package com.udpt.auth.controller;

import com.udpt.auth.dto.AuthenticationResponse;
import com.udpt.auth.dto.ResponseDto;
import com.udpt.auth.request.RefreshTokenRequest;
import com.udpt.auth.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private IAuthService authService;
    public LoginController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        AuthenticationResponse authenticationResponse = authService.login(email, password);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String newAccessToken = authService.refreshAccessToken(request.refreshToken());

        return ResponseEntity.ok().body(new ResponseDto("200", newAccessToken));
    }

}

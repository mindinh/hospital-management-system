package com.udpt.accounts.controller;


import com.udpt.accounts.dto.ResponseDto;
import com.udpt.accounts.request.AccountRegisterRequest;
import com.udpt.accounts.service.IRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    private IRegisterService registerService;
    public RegisterController(IRegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody AccountRegisterRequest request) {
        registerService.register(request);

        return ResponseEntity.ok(new ResponseDto("200", "Account registered successful"));
    }

}

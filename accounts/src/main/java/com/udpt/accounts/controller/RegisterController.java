package com.udpt.accounts.controller;


import com.udpt.accounts.dto.ResponseDto;
import com.udpt.accounts.request.AccountRegisterRequest;
import com.udpt.accounts.service.IRegisterService;
import com.udpt.accounts.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    private IRegisterService registerService;
    private OtpService otpService;
    public RegisterController(IRegisterService registerService, OtpService otpService) {
        this.registerService = registerService;
        this.otpService = otpService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestParam String email) {
//        registerService.register(request);
        otpService.generateOtp(email);

        return ResponseEntity.ok(new ResponseDto("200", "OTP đã gửi tới email"));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(email, otp);
        if (isValid) {
            return ResponseEntity.ok(new ResponseDto("200", "OTP hợp lệ."));
        }
        return ResponseEntity.badRequest().body(new ResponseDto("400", "OTP hết hạn hoặc ko hợp lệ."));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AccountRegisterRequest accountRegisterRequest) {
        registerService.register(accountRegisterRequest);

        return ResponseEntity.ok(new ResponseDto("201", "Đã tạo tài khoản."));
    }

    @PostMapping("/create/employee")
    public ResponseEntity<?> createEmployee(@RequestBody AccountRegisterRequest accountRegisterRequest) {
        registerService.register(accountRegisterRequest);

        return ResponseEntity.ok(new ResponseDto("201", "Đã tạo tài khoản nhân viên."));
    }
}

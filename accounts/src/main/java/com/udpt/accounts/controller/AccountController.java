package com.udpt.accounts.controller;


import com.udpt.accounts.dto.AccountDto;
import com.udpt.accounts.dto.ErrorResponseDto;
import com.udpt.accounts.dto.ResponseDto;
import com.udpt.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private IAccountsService accountsService;
    public AccountController(IAccountsService accountsService) {
        this.accountsService = accountsService;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createPatientAccount(@Valid @RequestBody AccountDto accountDto) {
        accountsService.createPatientAccount(accountDto);

        return ResponseEntity.ok(new ResponseDto("200", "Account created"));

    }

    @PostMapping("/employee/create")
    public ResponseEntity<?> createEmployeeAccount(@Valid @RequestBody AccountDto accountDto, @RequestParam String role) {
        accountsService.createEmployeeAccount(accountDto, role);

        return ResponseEntity.ok(new ResponseDto("200", "Account created"));
    }

    @PostMapping("/details")
    public ResponseEntity<?> getAccountDetails(@RequestParam String mobileNo) {

        return ResponseEntity.ok(accountsService.getAccountDetails(mobileNo));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountDto accountDto) {
        boolean isSuccess = false;
        isSuccess = accountsService.updateAccount(accountDto);
        if (!isSuccess) {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Account updated unsuccessful"));
        }
        else {
            return ResponseEntity.ok(new ResponseDto("200", "Account updated successful"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam String mobileNo) {
        boolean isSuccess = false;
        isSuccess = accountsService.deleteAccount(mobileNo);

        if (!isSuccess) {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Account deleted unsuccessful"));
        }
        else {
            return ResponseEntity.ok(new ResponseDto("200", "Account deleted successful"));
        }
    }
}

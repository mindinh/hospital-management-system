package com.udpt.accounts.service;

import com.udpt.accounts.dto.AccountDto;

public interface IAccountsService {
    void createPatientAccount(AccountDto accountDto);
    void createEmployeeAccount(AccountDto accountDto, String role);
    AccountDto getAccountDetails(String mobileNo);
    boolean updateAccount(AccountDto accountDto);
    boolean deleteAccount(String mobileNo);
}

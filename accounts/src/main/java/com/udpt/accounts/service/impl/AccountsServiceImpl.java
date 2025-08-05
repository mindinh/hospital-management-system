package com.udpt.accounts.service.impl;

import com.github.f4b6a3.uuid.UuidCreator;
import com.udpt.accounts.dto.AccountDto;
import com.udpt.accounts.entity.AccountEntity;
import com.udpt.accounts.entity.Role;
import com.udpt.accounts.entity.Status;
import com.udpt.accounts.exception.AccountAlreadyExistException;
import com.udpt.accounts.exception.ResourceNotFoundException;
import com.udpt.accounts.mapper.AccountMapper;
import com.udpt.accounts.repository.AccountRepository;
import com.udpt.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;

    @Override
    public void createPatientAccount(AccountDto accountDto) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmailAddress(accountDto.getEmail());
        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address");
        }
        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountDto, new AccountEntity());
        accountEntity.setUserId(UuidCreator.getTimeBased());
        accountEntity.setUsername(accountDto.getEmail().split("@")[0]);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRole(Role.PATIENT);
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("account-service");

        accountRepository.save(accountEntity);

    }

    @Override
    public void createEmployeeAccount(AccountDto accountDto, String role) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmailAddress(accountDto.getEmail());
        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address");
        }
        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountDto, new AccountEntity());
        accountEntity.setUserId(UuidCreator.getTimeBased());
        accountEntity.setUsername(accountDto.getEmail().split("@")[0]);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRole(Role.valueOf(role));
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("account-service");

        accountRepository.save(accountEntity);
    }

    @Override
    public AccountDto getAccountDetails(String mobileNo) {
        AccountEntity accountEntity = accountRepository.findByMobileNo(mobileNo).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Mobile number", mobileNo)
        );

        return AccountMapper.mapToAccountDto(accountEntity, new AccountDto());
    }

    @Override
    public boolean updateAccount(AccountDto accountDto) {
        AccountEntity accountEntity = accountRepository.findByMobileNo(accountDto.getMobileNo()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Mobile number", accountDto.getMobileNo())
        );

        AccountMapper.mapToAccountEntity(accountDto, accountEntity);
        accountRepository.save(accountEntity);
        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNo) {
        AccountEntity accountEntity = accountRepository.findByMobileNo(mobileNo).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Mobile number", mobileNo)
        );

        accountRepository.delete(accountEntity);
        return true;
    }
}

package com.udpt.accounts.service.impl;

import com.fasterxml.uuid.Generators;
import com.udpt.accounts.dto.AccountDto;
import com.udpt.accounts.entity.AccountEntity;
import com.udpt.accounts.entity.Role;
import com.udpt.accounts.entity.Status;
import com.udpt.accounts.event.events.AccountCreatedEvent;
import com.udpt.accounts.event.publisher.AccountEventPublisher;
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
    private AccountEventPublisher eventPublisher;

    @Override
    public void createPatientAccount(AccountDto accountDto) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmailAddress(accountDto.getEmail());
        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address");
        }
        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountDto, new AccountEntity());
        accountEntity.setUserId(Generators.timeBasedGenerator().generate());
        accountEntity.setUsername(accountDto.getEmail().split("@")[0]);
        accountEntity.setRole(Role.PATIENT);
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("account-service");

        accountRepository.save(accountEntity);

        AccountCreatedEvent event = new AccountCreatedEvent(
                accountEntity.getUserId(), accountEntity.getEmailAddress(), accountEntity.getMobileNo(), String.valueOf(Role.PATIENT)
        );
        eventPublisher.publishAccountCreated(event);

    }

    @Override
    public void createEmployeeAccount(AccountDto accountDto, String role) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmailAddress(accountDto.getEmail());
        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address");
        }
        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountDto, new AccountEntity());
        accountEntity.setUserId(Generators.timeBasedGenerator().generate());
        accountEntity.setUsername(accountDto.getEmail().split("@")[0]);
        accountEntity.setRole(Role.valueOf(role));
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("account-service");

        accountRepository.save(accountEntity);

        AccountCreatedEvent event = new AccountCreatedEvent(
                accountEntity.getUserId(), accountEntity.getEmailAddress(), accountEntity.getMobileNo(), String.valueOf(Role.valueOf(role))
        );
        eventPublisher.publishAccountCreated(event);
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

        accountEntity.setStatus(Status.INACTIVE);
        accountRepository.save(accountEntity);

        return true;
    }
}

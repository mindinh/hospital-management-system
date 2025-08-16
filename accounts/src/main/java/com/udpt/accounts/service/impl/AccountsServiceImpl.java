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
import com.udpt.accounts.utils.IdGenerator;
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
        Optional<AccountEntity> optionalAccount;
        if (accountDto.getEmail() != null) {
            optionalAccount = accountRepository.findByEmailAddress(accountDto.getEmail());
        }
        else {
            optionalAccount = accountRepository.findByMobileNo(accountDto.getSoDienThoai());
        }

        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address or mobile no.");
        }
        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountDto, new AccountEntity());

        accountEntity.setUserId(IdGenerator.generateAccountCode("BN"));
        accountEntity.setUsername(accountDto.getEmail().split("@")[0]);
        accountEntity.setRole(Role.BENHNHAN);
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("account-service");

        accountRepository.save(accountEntity);


    }

    @Override
    public void createEmployeeAccount(AccountDto accountDto, String role) {
        Optional<AccountEntity> optionalAccount = Optional.of(new AccountEntity());
        if (accountDto.getEmail() != null) {
            optionalAccount = accountRepository.findByEmailAddress(accountDto.getEmail());
        }
        else {
            optionalAccount = accountRepository.findByMobileNo(accountDto.getSoDienThoai());
        }

        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address or mobile no.");
        }

        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountDto, new AccountEntity());
        switch (role) {
            case "TIEPTAN":
                accountEntity.setRole(Role.TIEPTAN);
                accountEntity.setUserId(IdGenerator.generateAccountCode("TT"));
                break;
            case "DUOCSI":
                accountEntity.setRole(Role.DUOCSI);
                accountEntity.setUserId(IdGenerator.generateAccountCode("DS"));
                break;
            case "BACSI":
                accountEntity.setRole(Role.BACSI);
                accountEntity.setUserId(IdGenerator.generateAccountCode("BS"));
                break;
            case "ADMIN":
                accountEntity.setRole(Role.ADMIN);
                accountEntity.setUserId(IdGenerator.generateAccountCode("AD"));
                break;
        }

        accountEntity.setUsername(accountDto.getEmail().split("@")[0]);
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
        AccountEntity accountEntity = accountRepository.findByMobileNo(accountDto.getSoDienThoai()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Mobile number", accountDto.getSoDienThoai())
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

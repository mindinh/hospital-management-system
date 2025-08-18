package com.udpt.accounts.service.impl;

import com.udpt.accounts.entity.AccountEntity;
import com.udpt.accounts.entity.Role;
import com.udpt.accounts.entity.Status;
import com.udpt.accounts.exception.AccountAlreadyExistException;
import com.udpt.accounts.mapper.AccountMapper;
import com.udpt.accounts.repository.AccountRepository;
import com.udpt.accounts.request.AccountRegisterRequest;
import com.udpt.accounts.service.IRegisterService;
import com.udpt.accounts.utils.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements IRegisterService {

    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;

    @Override
    public void register(AccountRegisterRequest request) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmailAddress(request.email());

        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Account already exist with this email address");
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmailAddress(request.email());
        accountEntity.setPassword(passwordEncoder.encode(request.password()));
        accountEntity.setUserId(IdGenerator.generateAccountCode("BN"));
        accountEntity.setUsername(request.email().split("@")[0]);
        accountEntity.setRole(Role.BENHNHAN);
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("account-service");

        accountRepository.save(accountEntity);

    }

}

package com.udpt.accounts.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.accounts.config.RabbitMQConfig;
import com.udpt.accounts.entity.AccountEntity;
import com.udpt.accounts.entity.Role;
import com.udpt.accounts.entity.Status;
import com.udpt.accounts.event.events.AccountCreatedEvent;
import com.udpt.accounts.exception.AccountAlreadyExistException;
import com.udpt.accounts.mapper.AccountMapper;
import com.udpt.accounts.repository.AccountRepository;
import com.udpt.accounts.request.AccountRegisterRequest;
import com.udpt.accounts.service.IRegisterService;
import com.udpt.accounts.utils.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements IRegisterService {

    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    @Override
    public void register(AccountRegisterRequest request) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmailAddress(request.email());

        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistException("Tài khoản với email đã tồn tại.");
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmailAddress(request.email());
        accountEntity.setPassword(passwordEncoder.encode(request.password()));
        accountEntity.setUserId(IdGenerator.generateAccountCode("BN"));
        accountEntity.setUsername(request.email().split("@")[0]);
        accountEntity.setMobileNo(request.soDT());
        if (request.chucVu() == null) {
            accountEntity.setRole(Role.BENHNHAN);
        }
        else {
            accountEntity.setRole(Role.valueOf(request.chucVu()));
        }
        accountEntity.setStatus(Status.ACTIVE);

        accountEntity.setCreatedAt(LocalDateTime.now());
        accountEntity.setCreatedBy("accounts-service");

        AccountEntity entity = accountRepository.save(accountEntity);

        AccountCreatedEvent event = new AccountCreatedEvent(
                entity.getUserId(),
                entity.getEmailAddress(),
                request.hoTen(),
                request.soDT(),
                request.ngaySinh(),
                request.gioiTinh(),

                String.valueOf(entity.getRole())
        );
        try {
            String json = objectMapper.writeValueAsString(event);
            if (entity.getRole().equals(Role.BENHNHAN)) {
                rabbitTemplate.convertAndSend(
                        RabbitMQConfig.ACCOUNT_EXCHANGE,
                        RabbitMQConfig.ACCOUNT_ROUTING_KEY_PATIENT,
                        json
                );
            }
            else if (entity.getRole().equals(Role.BACSI) || entity.getRole().equals(Role.DUOCSI) || entity.getRole().equals(Role.TIEPTAN)) {
                rabbitTemplate.convertAndSend(
                        RabbitMQConfig.ACCOUNT_EXCHANGE,
                        RabbitMQConfig.ACCOUNT_ROUTING_KEY_EMPLOYEE,
                        json
                );
            }
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}

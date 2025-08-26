package com.udpt.employees.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.employees.entity.Role;
import com.udpt.employees.repository.EmployeesRepository;
import com.udpt.employees.config.RabbitMQConfig;
import com.udpt.employees.entity.Gender;
import com.udpt.employees.entity.EmployeeEntity;
import com.udpt.employees.event.events.AccountCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AccountCreatedListener {

    private final EmployeesRepository employeesRepository;
    private final ObjectMapper objectMapper;

    public AccountCreatedListener(EmployeesRepository employeesRepository, ObjectMapper objectMapper) {
        this.employeesRepository = employeesRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.EMPLOYEE_ACCOUNT_QUEUE)
    public void handleEmployeeAccountCreated(String messageJson) throws JsonProcessingException {
        AccountCreatedEvent accountCreatedEvent = objectMapper.readValue(messageJson, AccountCreatedEvent.class);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setMaNV(accountCreatedEvent.getAccountId());

        employeeEntity.setHoTenNV(accountCreatedEvent.getName());
        employeeEntity.setSoDTNV(accountCreatedEvent.getMobileNo());
        employeeEntity.setNgaySinhNV(LocalDate.parse(accountCreatedEvent.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        employeeEntity.setGioiTinhNV(Gender.valueOf(accountCreatedEvent.getGender()));
        employeeEntity.setChucVu(Role.valueOf(accountCreatedEvent.getAccountType()));

        employeeEntity.setCreatedAt(LocalDateTime.now());
        employeeEntity.setCreatedBy("employees-service");

        employeesRepository.save(employeeEntity);
    }
}

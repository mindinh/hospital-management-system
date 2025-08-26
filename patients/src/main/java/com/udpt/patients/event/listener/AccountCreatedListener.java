package com.udpt.patients.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.patients.config.RabbitMQConfig;
import com.udpt.patients.entity.Gender;
import com.udpt.patients.entity.PatientEntity;
import com.udpt.patients.event.events.AccountCreatedEvent;
import com.udpt.patients.repository.PatientsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AccountCreatedListener {

    private final PatientsRepository patientsRepository;
    private final ObjectMapper objectMapper;

    public AccountCreatedListener(PatientsRepository patientsRepository, ObjectMapper objectMapper) {
        this.patientsRepository = patientsRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.PATIENT_ACCOUNT_QUEUE)
    public void handlePatientAccountCreated(String messageJson) throws JsonProcessingException {
        AccountCreatedEvent accountCreatedEvent = objectMapper.readValue(messageJson, AccountCreatedEvent.class);

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setPatientId(accountCreatedEvent.getAccountId());
        patientEntity.setPatientEmail(accountCreatedEvent.getEmailAddr());
        patientEntity.setPatientFullname(accountCreatedEvent.getName());
        patientEntity.setPatientMobileNo(accountCreatedEvent.getMobileNo());
        patientEntity.setPatientDOB(LocalDate.parse(accountCreatedEvent.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        patientEntity.setGender(Gender.valueOf(accountCreatedEvent.getGender()));

        patientEntity.setCreatedAt(LocalDateTime.now());
        patientEntity.setCreatedBy("patients-service");

        patientsRepository.save(patientEntity);
    }
}

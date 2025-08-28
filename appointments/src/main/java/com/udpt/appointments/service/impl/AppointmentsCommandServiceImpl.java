package com.udpt.appointments.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.appointments.config.RabbitMQConfig;
import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.dto.ExamFormDto;
import com.udpt.appointments.entity.write.AppointmentEntity;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.entity.write.ExaminationFormEntity;
import com.udpt.appointments.event.events.AppointmentCreatedEvent;
import com.udpt.appointments.event.events.AppointmentReminderEvent;
import com.udpt.appointments.event.events.AppointmentUpdatedEvent;
import com.udpt.appointments.event.publisher.AppointmentEventPublisher;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.write.AppointmentsWriteRepository;
import com.udpt.appointments.repository.write.ExamFormsRepository;
import com.udpt.appointments.response.DoctorResponse;
import com.udpt.appointments.response.PatientResponse;
import com.udpt.appointments.service.CounterService;
import com.udpt.appointments.service.IAppointmentsCommandService;
import com.udpt.appointments.service.client.DoctorClient;
import com.udpt.appointments.service.client.PatientClient;
import com.udpt.appointments.utils.IdGenerator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentsCommandServiceImpl implements IAppointmentsCommandService {

    private DoctorClient doctorClient;
    private PatientClient patientClient;
    private AppointmentsWriteRepository appointmentsWriteRepository;
    private ExamFormsRepository examFormsRepository;
    private RabbitTemplate rabbitTemplate;
    private CounterService counterService;
    private AppointmentEventPublisher appointmentEventPublisher;

    public AppointmentsCommandServiceImpl(
            DoctorClient doctorClient,
            PatientClient patientClient,
            AppointmentsWriteRepository appointmentsWriteRepository,
            ExamFormsRepository examFormsRepository,
            RabbitTemplate rabbitTemplate,
            CounterService counterService,
            AppointmentEventPublisher appointmentEventPublisher) {
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
        this.appointmentsWriteRepository = appointmentsWriteRepository;
        this.examFormsRepository = examFormsRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.counterService = counterService;
        this.appointmentEventPublisher = appointmentEventPublisher;
    }

    @Override
    public void createAppointment(CreateAppointmentCommand command) {
        DoctorResponse doctorResponse = doctorClient.getDoctorDetails(command.getMaBacSi());
        if (doctorResponse == null) {
            throw new ResourceNotFoundException("Bac Si", "Ma Bac Si", command.getMaBacSi());
        }

        PatientResponse patientResponse = patientClient.getPatientDetailsById(command.getMaBenhNhan());
        if (patientResponse == null) {
            throw new ResourceNotFoundException("Benh Nhan", "Ma Benh Nhan", command.getMaBenhNhan());
        }

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentId(IdGenerator.generateCode("AP"));
        appointment.setPatientId(command.getMaBenhNhan());
        appointment.setDoctorId(command.getMaBacSi());
        appointment.setAppointmentDate(command.getNgayKham());
        appointment.setAppointmentTime(command.getGioKham());
        appointment.setAppointmentNotes(command.getGhiChu());
        appointment.setRoomNo(command.getPhong());
        appointment.setStatus(Status.DA_DAT);

        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setCreatedBy("appointments-service");

        AppointmentEntity entity = appointmentsWriteRepository.save(appointment);

        AppointmentCreatedEvent event = new AppointmentCreatedEvent(
                entity.getAppointmentId(),
                entity.getPatientId(),
                patientResponse.getHoTen(),
                patientResponse.getSoDienThoai(),
                patientResponse.getEmailBenhNhan(),
                entity.getDoctorId(),
                doctorResponse.getHoTen(),
                doctorResponse.getChuyenKhoa(),
                entity.getAppointmentNotes(),
                String.valueOf(entity.getStatus()),
                entity.getAppointmentDate(),
                entity.getAppointmentTime()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APPOINTMENT_EXCHANGE,
                RabbitMQConfig.APPOINTMENT_ROUTING_KEY,
                event
        );

        AppointmentReminderEvent reminderEvent = new AppointmentReminderEvent(
                entity.getAppointmentId(),
                doctorResponse.getHoTen(),
                doctorResponse.getChuyenKhoa(),
                patientResponse.getHoTen(),
                patientResponse.getEmailBenhNhan(),
                LocalDateTime.of(entity.getAppointmentDate(), entity.getAppointmentTime())
        );
        appointmentEventPublisher.sendReminderEvent(reminderEvent);


    }

    @Override
    public void bookAppointment(CreateAppointmentCommand command, Authentication authentication) {
        DoctorResponse doctorResponse = doctorClient.getDoctorDetails(command.getMaBacSi());
        if (doctorResponse == null) {
            throw new ResourceNotFoundException("Bac Si", "Ma Bac Si", command.getMaBacSi());
        }

        PatientResponse patientResponse = patientClient.getPatientDetails();
        if (patientResponse == null) {
            throw new ResourceNotFoundException("Benh Nhan", "Ma Benh Nhan", command.getMaBenhNhan());
        }

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentId(IdGenerator.generateCode("AP"));
        appointment.setPatientId(authentication.getName());
        appointment.setDoctorId(command.getMaBacSi());
        appointment.setAppointmentDate(command.getNgayKham());
        appointment.setAppointmentTime(command.getGioKham());
        appointment.setAppointmentNotes(command.getGhiChu());
        appointment.setRoomNo(command.getPhong());
        appointment.setStatus(Status.DA_DAT);

        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setCreatedBy("patients-booking");

        AppointmentEntity entity = appointmentsWriteRepository.save(appointment);

        AppointmentCreatedEvent event = new AppointmentCreatedEvent(
                entity.getAppointmentId(),
                entity.getPatientId(),
                patientResponse.getHoTen(),
                patientResponse.getSoDienThoai(),
                patientResponse.getEmailBenhNhan(),
                entity.getDoctorId(),
                doctorResponse.getHoTen(),
                doctorResponse.getChuyenKhoa(),
                entity.getAppointmentNotes(),
                String.valueOf(entity.getStatus()),
                entity.getAppointmentDate(),
                entity.getAppointmentTime()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APPOINTMENT_EXCHANGE,
                RabbitMQConfig.APPOINTMENT_ROUTING_KEY,
                event
        );
    }

    @Override
    public ExamFormDto checkinAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentsWriteRepository.findByAppointmentId(id).orElseThrow(
                () -> new ResourceNotFoundException("Lich Kham", "Ma Lich Kham", id)
        );

        appointmentEntity.setStatus(Status.DA_THANH_TOAN);
        appointmentEntity.setUpdatedAt(LocalDateTime.now());
        appointmentEntity.setUpdatedBy("appointments-service");

        AppointmentEntity entity = appointmentsWriteRepository.save(appointmentEntity);

        AppointmentUpdatedEvent event = new AppointmentUpdatedEvent(
                entity.getAppointmentId(),
                String.valueOf(entity.getStatus())
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APPOINTMENT_EXCHANGE,
                RabbitMQConfig.APPOINTMENT_UPDATED_ROUTING_KEY,
                event
        );
        DoctorResponse doctorResponse = doctorClient.getDoctorDetails(entity.getDoctorId());
        PatientResponse patientResponse = patientClient.getPatientDetailsById(entity.getPatientId());


        ExaminationFormEntity examinationForm = new ExaminationFormEntity();
        examinationForm.setFormId(IdGenerator.generateCode("EF"));
        examinationForm.setDoctorId(entity.getDoctorId());
        examinationForm.setPatientId(entity.getPatientId());
        examinationForm.setAppointmentDate(entity.getAppointmentDate());
        examinationForm.setAppointmentTime(entity.getAppointmentTime());
        examinationForm.setRoomNumber(entity.getRoomNo());
        examinationForm.setNumber(String.valueOf(counterService.getNextCounter(entity.getRoomNo())));
        examinationForm.setAppointment(entity);
        examinationForm.setStatus(Status.DOI_KHAM);

        examinationForm.setCreatedAt(LocalDateTime.now());
        examinationForm.setCreatedBy("appointments-service");

        examFormsRepository.save(examinationForm);

        return new ExamFormDto(
                examinationForm.getFormId(),
                doctorResponse.getHoTen(),
                patientResponse.getHoTen(),
                String.valueOf(examinationForm.getAppointmentDate()),
                String.valueOf(examinationForm.getAppointmentTime()),
                examinationForm.getRoomNumber(),
                examinationForm.getNumber()
        );

    }

    @Override
    public void cancelAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentsWriteRepository.findByAppointmentId(id).orElseThrow(
                () -> new ResourceNotFoundException("Lich Kham", "Ma Lich Kham", id)
        );

        appointmentEntity.setStatus(Status.DA_HUY);
        appointmentEntity.setUpdatedAt(LocalDateTime.now());
        appointmentEntity.setUpdatedBy("appointments-service");

        AppointmentEntity entity = appointmentsWriteRepository.save(appointmentEntity);

        AppointmentUpdatedEvent event = new AppointmentUpdatedEvent(
                entity.getAppointmentId(),
                String.valueOf(entity.getStatus())
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APPOINTMENT_EXCHANGE,
                RabbitMQConfig.APPOINTMENT_UPDATED_ROUTING_KEY,
                event
        );
    }


}

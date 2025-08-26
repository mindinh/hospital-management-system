package com.udpt.appointments.service.impl;

import com.udpt.appointments.config.RabbitMQConfig;
import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.entity.write.AppointmentEntity;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.event.events.AppointmentCreatedEvent;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.write.AppointmentsWriteRepository;
import com.udpt.appointments.response.DoctorResponse;
import com.udpt.appointments.response.PatientResponse;
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
    private RabbitTemplate rabbitTemplate;

    public AppointmentsCommandServiceImpl(DoctorClient doctorClient, PatientClient patientClient, AppointmentsWriteRepository appointmentsWriteRepository, RabbitTemplate rabbitTemplate) {
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
        this.appointmentsWriteRepository = appointmentsWriteRepository;
        this.rabbitTemplate = rabbitTemplate;
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
        appointment.setAppointmentId(IdGenerator.generateAccountCode("AP"));
        appointment.setPatientId(command.getMaBenhNhan());
        appointment.setDoctorId(command.getMaBacSi());
        appointment.setAppointmentDate(command.getNgayKham());
        appointment.setAppointmentTime(command.getGioKham());
        appointment.setAppointmentNotes(command.getGhiChu());
        appointment.setStatus(Status.DA_DAT);

        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setCreatedBy("appointments-service");

        AppointmentEntity entity = appointmentsWriteRepository.save(appointment);

        AppointmentCreatedEvent event = new AppointmentCreatedEvent(
                entity.getAppointmentId(),
                entity.getPatientId(),
                patientResponse.getHoTen(),
                patientResponse.getSoDienThoai(),
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
        appointment.setAppointmentId(IdGenerator.generateAccountCode("AP"));
        appointment.setPatientId(authentication.getName());
        appointment.setDoctorId(command.getMaBacSi());
        appointment.setAppointmentDate(command.getNgayKham());
        appointment.setAppointmentTime(command.getGioKham());
        appointment.setAppointmentNotes(command.getGhiChu());
        appointment.setStatus(Status.DA_DAT);

        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setCreatedBy("appointments-service");

        appointmentsWriteRepository.save(appointment);
    }

    @Override
    public boolean checkinAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentsWriteRepository.findByAppointmentId(id).orElseThrow(
                () -> new ResourceNotFoundException("Lich Kham", "Ma Lich Kham", id)
        );

        appointmentEntity.setStatus(Status.DA_THANH_TOAN);
        appointmentEntity.setUpdatedAt(LocalDateTime.now());
        appointmentEntity.setUpdatedBy("appointments-service");

        appointmentsWriteRepository.save(appointmentEntity);
        return true;
    }

    @Override
    public boolean cancelAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentsWriteRepository.findByAppointmentId(id).orElseThrow(
                () -> new ResourceNotFoundException("Lich Kham", "Ma Lich Kham", id)
        );

        appointmentEntity.setStatus(Status.DA_HUY);
        appointmentEntity.setUpdatedAt(LocalDateTime.now());
        appointmentEntity.setUpdatedBy("appointments-service");

        appointmentsWriteRepository.save(appointmentEntity);
        return true;
    }


}

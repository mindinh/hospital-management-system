package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.entity.AppointmentEntity;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.AppointmentsRepository;
import com.udpt.appointments.response.DoctorResponse;
import com.udpt.appointments.response.PatientResponse;
import com.udpt.appointments.service.IAppointmentsCommandService;
import com.udpt.appointments.service.client.DoctorClient;
import com.udpt.appointments.service.client.PatientClient;
import com.udpt.appointments.utils.IdGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentsCommandServiceImpl implements IAppointmentsCommandService {

    private DoctorClient doctorClient;
    private PatientClient patientClient;
    private AppointmentsRepository appointmentsRepository;

    public AppointmentsCommandServiceImpl(DoctorClient doctorClient, PatientClient patientClient, AppointmentsRepository appointmentsRepository) {
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
        this.appointmentsRepository = appointmentsRepository;

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

        appointmentsRepository.save(appointment);

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

        appointmentsRepository.save(appointment);
    }

    @Override
    public boolean checkinAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentsRepository.findByAppointmentId(id).orElseThrow(
                () -> new ResourceNotFoundException("Lich Kham", "Ma Lich Kham", id)
        );

        appointmentEntity.setStatus(Status.DA_THANH_TOAN);
        appointmentEntity.setUpdatedAt(LocalDateTime.now());
        appointmentEntity.setUpdatedBy("appointments-service");

        appointmentsRepository.save(appointmentEntity);
        return true;
    }

    @Override
    public boolean cancelAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentsRepository.findByAppointmentId(id).orElseThrow(
                () -> new ResourceNotFoundException("Lich Kham", "Ma Lich Kham", id)
        );

        appointmentEntity.setStatus(Status.DA_HUY);
        appointmentEntity.setUpdatedAt(LocalDateTime.now());
        appointmentEntity.setUpdatedBy("appointments-service");

        appointmentsRepository.save(appointmentEntity);
        return true;
    }


}

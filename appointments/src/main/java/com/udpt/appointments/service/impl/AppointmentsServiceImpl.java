package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.CreateAppointmentCommand;
import com.udpt.appointments.entity.AppointmentEntity;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.AppointmentsRepository;
import com.udpt.appointments.request.AppointmentInsertRequest;
import com.udpt.appointments.response.DoctorResponse;
import com.udpt.appointments.response.PatientResponse;
import com.udpt.appointments.service.IAppointmentsCommandService;
import com.udpt.appointments.service.IAppointmentsQueryService;
import com.udpt.appointments.service.client.DoctorClient;
import com.udpt.appointments.service.client.PatientClient;
import com.udpt.appointments.utils.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AppointmentsServiceImpl implements IAppointmentsCommandService, IAppointmentsQueryService {

    private DoctorClient doctorClient;
    private PatientClient patientClient;
    private AppointmentsRepository appointmentsRepository;

    public AppointmentsServiceImpl(DoctorClient doctorClient, PatientClient patientClient, AppointmentsRepository appointmentsRepository) {
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
        appointment.setMaLichKham(IdGenerator.generateAccountCode("LK"));
        appointment.setMaBenhNhan(command.getMaBenhNhan());
        appointment.setMaBacSi(command.getMaBacSi());
        appointment.setNgayKham(command.getNgayKham());
        appointment.setGioKham(command.getGioKham());
        appointment.setGhiChuKham(command.getGhiChu());
        appointment.setTrangThai(Status.DA_DAT);

        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setCreatedBy("appointments-service");

        appointmentsRepository.save(appointment);

    }

    @Override
    public int countPatientsByDoctorAndDateRange(String maBacSi, LocalDate startDate, LocalDate endDate){
        return appointmentsRepository.countPatientsByDoctorAndDateRange(maBacSi, startDate, endDate);
    }

    @Override
    public int countPatientsByDateRange(LocalDate startDate, LocalDate endDate){
        return appointmentsRepository.countPatientsByDateRange(startDate, endDate);
    }
}

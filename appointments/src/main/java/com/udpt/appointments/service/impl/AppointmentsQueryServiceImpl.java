package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.AppointmentDto;
import com.udpt.appointments.entity.AppointmentEntity;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.repository.AppointmentsRepository;
import com.udpt.appointments.service.IAppointmentsQueryService;
import com.udpt.appointments.utils.AppointmentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentsQueryServiceImpl implements IAppointmentsQueryService {

    private AppointmentsRepository appointmentsRepository;
    public AppointmentsQueryServiceImpl(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    @Override
    public Page<AppointmentDto> searchAppointments(String doctorId, String patientId, LocalDate startDate, LocalDate endDate, String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("appointmentId").descending());
        Page<AppointmentEntity> appointments =  appointmentsRepository.findAll(
                AppointmentSpecification.filter(doctorId, patientId, startDate, endDate, Status.valueOf(status)),
                pageable
        );

        return appointments.map(
                a -> {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setMaBacSi(a.getDoctorId());
                    appointmentDto.setMaBenhNhan(a.getPatientId());
                    appointmentDto.setMaLichKham(a.getAppointmentId());
                    appointmentDto.setNgayKham(a.getAppointmentDate());
                    appointmentDto.setGioKham(a.getAppointmentTime());
                    appointmentDto.setTinhTrang(String.valueOf(a.getStatus()));

                    return appointmentDto;
                }
        );

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

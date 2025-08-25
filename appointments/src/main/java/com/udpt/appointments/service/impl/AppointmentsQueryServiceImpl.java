package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.AppointmentDto;
import com.udpt.appointments.entity.read.AppointmentViewEntity;
import com.udpt.appointments.entity.write.AppointmentEntity;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.repository.read.AppointmentsReadRepository;
import com.udpt.appointments.repository.write.AppointmentsWriteRepository;
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

    private AppointmentsReadRepository appointmentsReadRepository;
    private AppointmentsWriteRepository appointmentsWriteRepository;
    public AppointmentsQueryServiceImpl(AppointmentsReadRepository appointmentsReadRepository, AppointmentsWriteRepository appointmentsWriteRepository) {
        this.appointmentsReadRepository = appointmentsReadRepository;
        this.appointmentsWriteRepository = appointmentsWriteRepository;
    }

    @Override
    public Page<AppointmentDto> searchAppointments(String doctorId, String patientId, LocalDate startDate, LocalDate endDate, String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("appointmentId").descending());
        Page<AppointmentViewEntity> appointments =  appointmentsReadRepository.findAll(
                AppointmentSpecification.filter(doctorId, patientId, startDate, endDate, Status.valueOf(status)),
                pageable
        );

        return appointments.map(
                a -> {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setMaBacSi(a.getDoctorId());
                    appointmentDto.setTenBacSi(a.getDoctorName());
                    appointmentDto.setMaBenhNhan(a.getPatientId());
                    appointmentDto.setTenBenhNhan(a.getPatientName());
                    appointmentDto.setSoDienThoaiBenhNhan(a.getPatientMobileNo());
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
        return appointmentsWriteRepository.countPatientsByDoctorAndDateRange(maBacSi, startDate, endDate);
    }

    @Override
    public int countPatientsByDateRange(LocalDate startDate, LocalDate endDate){
        return appointmentsWriteRepository.countPatientsByDateRange(startDate, endDate);
    }
}

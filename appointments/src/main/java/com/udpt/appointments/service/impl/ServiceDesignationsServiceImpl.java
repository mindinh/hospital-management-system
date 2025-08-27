package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.CreateServiceDesignationCommand;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.entity.read.AppointmentViewEntity;
import com.udpt.appointments.entity.write.AppointmentEntity;
import com.udpt.appointments.entity.write.ServiceDesignationEntity;
import com.udpt.appointments.entity.write.ServiceEntity;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.read.AppointmentsReadRepository;
import com.udpt.appointments.repository.write.AppointmentsWriteRepository;
import com.udpt.appointments.repository.write.ExamFormsRepository;
import com.udpt.appointments.repository.write.ServiceDesignationsRepository;
import com.udpt.appointments.repository.write.ServicesRepository;
import com.udpt.appointments.request.UpdateServiceDesignationResult;
import com.udpt.appointments.service.CounterService;
import com.udpt.appointments.service.IServiceDesignationsService;
import com.udpt.appointments.utils.IdGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceDesignationsServiceImpl implements IServiceDesignationsService {

    private ServicesRepository servicesRepository;
    private AppointmentsWriteRepository appointmentsWriteRepository;
    private ServiceDesignationsRepository designationsRepository;
    private CounterService counterService;

    public ServiceDesignationsServiceImpl(
            ServicesRepository servicesRepository,
            AppointmentsWriteRepository appointmentsWriteRepository,
            ServiceDesignationsRepository designationsRepository,
            CounterService counterService
    ) {
        this.servicesRepository = servicesRepository;
        this.appointmentsWriteRepository = appointmentsWriteRepository;
        this.designationsRepository = designationsRepository;
        this.counterService = counterService;
    }

    @Override
    public void createServiceDesignation(CreateServiceDesignationCommand command) {
        ServiceEntity serviceEntity = servicesRepository.findById(command.getMaDichVu()).orElseThrow(
                () -> new ResourceNotFoundException("Dich Vu", "Ma Dich Vu", String.valueOf(command.getMaDichVu()))
        );

        AppointmentEntity appointmentEntity = appointmentsWriteRepository.findByAppointmentId(command.getMaLichKham()).orElseThrow(
                () -> new ResourceNotFoundException("Phieu Kham", "Ma Phieu Kham", command.getMaLichKham())
        );

        ServiceDesignationEntity designationEntity = new ServiceDesignationEntity();
        designationEntity.setId(IdGenerator.generateCode("SD"));
        designationEntity.setAppointment(appointmentEntity);
        designationEntity.setService(serviceEntity);
        designationEntity.setServiceName(serviceEntity.getTenDichVu());
        designationEntity.setDescription(command.getMoTa());
        designationEntity.setRoomNo(serviceEntity.getPhong());
        designationEntity.setNumber(String.valueOf(counterService.getNextCounter(serviceEntity.getPhong())));
        designationEntity.setStatus(Status.DOI_KHAM);

        designationEntity.setCreatedAt(LocalDateTime.now());
        designationEntity.setCreatedBy("appointments-service");

        designationsRepository.save(designationEntity);

    }

    @Override
    public void updateServiceDesignationResult(String id, UpdateServiceDesignationResult result) {
        ServiceDesignationEntity designationEntity = designationsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Chi Dinh", "Id", id)
        );

        designationEntity.setResult(result.ketQua());
        designationEntity.setStatus(Status.DA_KHAM);
        designationEntity.setUpdatedAt(LocalDateTime.now());
        designationEntity.setUpdatedBy("appointments-service");

        designationsRepository.save(designationEntity);
    }
}

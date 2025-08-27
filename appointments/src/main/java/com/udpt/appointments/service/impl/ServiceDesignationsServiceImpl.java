package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.CreateServiceDesignationCommand;
import com.udpt.appointments.entity.write.ExaminationFormEntity;
import com.udpt.appointments.entity.write.ServiceDesignationEntity;
import com.udpt.appointments.entity.write.ServiceEntity;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.write.ExamFormsRepository;
import com.udpt.appointments.repository.write.ServiceDesignationsRepository;
import com.udpt.appointments.repository.write.ServicesRepository;
import com.udpt.appointments.service.CounterService;
import com.udpt.appointments.service.IServiceDesignationsService;
import com.udpt.appointments.utils.IdGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceDesignationsServiceImpl implements IServiceDesignationsService {

    private ServicesRepository servicesRepository;
    private ExamFormsRepository examFormsRepository;
    private ServiceDesignationsRepository designationsRepository;
    private CounterService counterService;

    public ServiceDesignationsServiceImpl(
            ServicesRepository servicesRepository,
            ExamFormsRepository examFormsRepository,
            ServiceDesignationsRepository designationsRepository,
            CounterService counterService
    ) {
        this.servicesRepository = servicesRepository;
        this.examFormsRepository = examFormsRepository;
        this.designationsRepository = designationsRepository;
        this.counterService = counterService;
    }

    @Override
    public void createServiceDesignation(CreateServiceDesignationCommand command) {
        ServiceEntity serviceEntity = servicesRepository.findById(command.getMaDichVu()).orElseThrow(
                () -> new ResourceNotFoundException("Dich Vu", "Ma Dich Vu", String.valueOf(command.getMaDichVu()))
        );

        ExaminationFormEntity formEntity = examFormsRepository.findByFormId(command.getMaPhieuKham()).orElseThrow(
                () -> new ResourceNotFoundException("Phieu Kham", "Ma Phieu Kham", command.getMaPhieuKham())
        );

        ServiceDesignationEntity designationEntity = new ServiceDesignationEntity();
        designationEntity.setId(IdGenerator.generateCode("SD"));
        designationEntity.setForm(formEntity);
        designationEntity.setService(serviceEntity);
        designationEntity.setServiceName(serviceEntity.getTenDichVu());
        designationEntity.setDescription(command.getMoTa());
        designationEntity.setRoomNo(serviceEntity.getPhong());
        designationEntity.setNumber(String.valueOf(counterService.getNextCounter(serviceEntity.getPhong())));

        designationEntity.setCreatedAt(LocalDateTime.now());
        designationEntity.setCreatedBy("appointments-service");

        designationsRepository.save(designationEntity);

    }
}

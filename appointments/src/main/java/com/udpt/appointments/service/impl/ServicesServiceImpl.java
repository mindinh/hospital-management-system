package com.udpt.appointments.service.impl;

import com.udpt.appointments.dto.ServiceDto;
import com.udpt.appointments.entity.write.ServiceEntity;
import com.udpt.appointments.exception.ResourceNotFoundException;
import com.udpt.appointments.repository.write.ServicesRepository;
import com.udpt.appointments.request.ServiceInsertRequest;
import com.udpt.appointments.service.IServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements IServicesService {

    private ServicesRepository servicesRepository;

    @Override
    public List<ServiceDto> getAllServices() {
        return servicesRepository.findAll().stream().map(
                entity -> {
                    ServiceDto serviceDto = new ServiceDto();
                    serviceDto.setTenDichVu(entity.getTenDichVu());
                    serviceDto.setMoTa(entity.getMoTaDV());

                    return serviceDto;
                }
        ).toList();
    }

    @Override
    public ServiceDto getServiceByName(String name) {
        ServiceEntity serviceEntity = servicesRepository.findByTenDichVu(name).orElseThrow(
                () -> new ResourceNotFoundException("Dich Vu", "Ten Dich Vu", name)
        );

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setTenDichVu(serviceEntity.getTenDichVu());
        serviceDto.setMoTa(serviceEntity.getMoTaDV());

        return serviceDto;
    }

    @Override
    public void createNewService(ServiceInsertRequest request) {
        Optional<ServiceEntity> serviceEntity = servicesRepository.findByTenDichVu(request.tenDichVu());
        if (serviceEntity.isPresent()) {
            throw new RuntimeException("service already exists");
        }

        ServiceEntity newService = new ServiceEntity();
        newService.setTenDichVu(request.tenDichVu());
        newService.setMoTaDV(request.moTaDichVu());
        newService.setCreatedAt(LocalDateTime.now());
        newService.setCreatedBy("appointments-service");
        servicesRepository.save(newService);

    }
}

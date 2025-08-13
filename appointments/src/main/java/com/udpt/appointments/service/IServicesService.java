package com.udpt.appointments.service;

import com.udpt.appointments.dto.ServiceDto;
import com.udpt.appointments.request.ServiceInsertRequest;

import java.util.List;

public interface IServicesService {
    List<ServiceDto> getAllServices();
    ServiceDto getServiceByName(String name);
    void createNewService(ServiceInsertRequest request);
}

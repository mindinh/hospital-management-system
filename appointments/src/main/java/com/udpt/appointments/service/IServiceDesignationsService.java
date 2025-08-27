package com.udpt.appointments.service;

import com.udpt.appointments.dto.CreateServiceDesignationCommand;
import com.udpt.appointments.request.UpdateServiceDesignationResult;

public interface IServiceDesignationsService {
    void createServiceDesignation(CreateServiceDesignationCommand command);
    void updateServiceDesignationResult(String id, UpdateServiceDesignationResult result);
}

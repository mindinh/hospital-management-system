package com.udpt.appointments.service;

import com.udpt.appointments.dto.CreateServiceDesignationCommand;

public interface IServiceDesignationsService {
    void createServiceDesignation(CreateServiceDesignationCommand command);
}

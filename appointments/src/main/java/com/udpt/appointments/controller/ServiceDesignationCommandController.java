package com.udpt.appointments.controller;

import com.udpt.appointments.dto.CreateServiceDesignationCommand;
import com.udpt.appointments.dto.ResponseDto;
import com.udpt.appointments.service.IServiceDesignationsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(value = "/api/v1/service-designation", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ServiceDesignationCommandController {

    private IServiceDesignationsService serviceDesignationsService;
    public ServiceDesignationCommandController(IServiceDesignationsService serviceDesignationsService) {
        this.serviceDesignationsService = serviceDesignationsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createServiceDesignation(@RequestBody CreateServiceDesignationCommand command) {
        serviceDesignationsService.createServiceDesignation(command);

        return ResponseEntity.ok(new ResponseDto("201", "Đã tạo chỉ định dịch vụ"));
    }

}

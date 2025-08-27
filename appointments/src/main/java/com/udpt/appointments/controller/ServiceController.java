package com.udpt.appointments.controller;


import com.udpt.appointments.dto.ResponseDto;
import com.udpt.appointments.request.ServiceInsertRequest;
import com.udpt.appointments.service.IServicesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/services", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ServiceController {

    private IServicesService servicesService;
    public ServiceController(IServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllServices() {

        return ResponseEntity.ok(servicesService.getAllServices());
    }

    @GetMapping("/{ten}")
    public ResponseEntity<?> getService(@PathVariable String ten) {

        return ResponseEntity.ok(servicesService.getServiceByName(ten));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewService(@RequestBody ServiceInsertRequest request) {
        servicesService.createNewService(request);

        return ResponseEntity.ok(new ResponseDto("200", "Service added successfully"));
    }

}

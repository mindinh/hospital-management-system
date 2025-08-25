package com.udpt.appointments.service.client;

import com.udpt.appointments.response.DoctorResponse;
import com.udpt.appointments.response.DoctorScheduleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees")
public interface DoctorClient {

    @GetMapping("api/v1/employees/doctor/{id}")
    public DoctorResponse getDoctorDetails(@PathVariable String id);

    @GetMapping("api/v1/schedules/doctor/{maNV}")
    public DoctorScheduleResponse getDoctorSchedules(@PathVariable String maNV);

}

package com.udpt.appointments.service.client;

import com.udpt.appointments.response.DoctorResponse;
import com.udpt.appointments.response.DoctorScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class DoctorFallback implements DoctorClient{

    @Override
    public DoctorResponse getDoctorDetails(String id) {
        return null;
    }

    @Override
    public DoctorScheduleResponse getDoctorSchedules(String maNV) {
        return null;
    }
}

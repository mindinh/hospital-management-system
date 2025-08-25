package com.udpt.appointments.event.listener;

import com.udpt.appointments.config.RabbitMQConfig;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.entity.read.AppointmentViewEntity;
import com.udpt.appointments.event.events.AppointmentCreatedEvent;
import com.udpt.appointments.repository.read.AppointmentsReadRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentEventListener {

    private final AppointmentsReadRepository appointmentsReadRepository;

    public AppointmentEventListener(AppointmentsReadRepository appointmentsReadRepository) {
        this.appointmentsReadRepository = appointmentsReadRepository;
    }

    @RabbitListener(
            queues = RabbitMQConfig.APPOINTMENT_QUEUE,
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handleAppointmentCreated(AppointmentCreatedEvent event) {
        AppointmentViewEntity appointmentView = new AppointmentViewEntity();
        appointmentView.setAppointmentId(event.getMaLichKham());
        appointmentView.setDoctorId(event.getMaBacSi());
        appointmentView.setDoctorName(event.getTenBacSi());
        appointmentView.setPatientId(event.getMaBenhNhan());
        appointmentView.setPatientName(event.getTenBacSi());
        appointmentView.setPatientMobileNo(event.getSoDTBenhNhan());
        appointmentView.setAppointmentNotes(event.getGhiChu());
        appointmentView.setStatus(Status.valueOf(event.getTinhTrang()));
        appointmentView.setAppointmentDate(event.getNgayKham());
        appointmentView.setAppointmentTime(event.getGioKham());

        appointmentView.setCreatedAt(LocalDateTime.now());
        appointmentView.setCreatedBy("appointments-read");

        appointmentsReadRepository.save(appointmentView);

    }

}

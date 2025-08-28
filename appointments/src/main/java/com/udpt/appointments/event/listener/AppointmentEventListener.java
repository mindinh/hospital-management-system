package com.udpt.appointments.event.listener;

import com.udpt.appointments.config.RabbitMQConfig;
import com.udpt.appointments.entity.Status;
import com.udpt.appointments.entity.read.AppointmentViewEntity;
import com.udpt.appointments.event.events.AppointmentCreatedEvent;
import com.udpt.appointments.event.events.AppointmentUpdatedEvent;
import com.udpt.appointments.repository.read.AppointmentsReadRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AppointmentEventListener {

    private final AppointmentsReadRepository appointmentsReadRepository;

    public AppointmentEventListener(AppointmentsReadRepository appointmentsReadRepository) {
        this.appointmentsReadRepository = appointmentsReadRepository;
    }

    @RabbitListener(
            queues = RabbitMQConfig.APPOINTMENT_CREATED_QUEUE,
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handleAppointmentCreated(AppointmentCreatedEvent event) {
        AppointmentViewEntity appointmentView = new AppointmentViewEntity();
        appointmentView.setAppointmentId(event.getMaLichKham());
        appointmentView.setDoctorId(event.getMaBacSi());
        appointmentView.setDoctorName(event.getTenBacSi());
        appointmentView.setDepartment(event.getChuyenKhoa());
        appointmentView.setPatientId(event.getMaBenhNhan());
        appointmentView.setPatientName(event.getTenBenhNhan());
        appointmentView.setPatientMobileNo(event.getSoDTBenhNhan());
        appointmentView.setPatientEmail(event.getEmailBenhNhan());
        appointmentView.setAppointmentNotes(event.getGhiChu());
        appointmentView.setStatus(Status.valueOf(event.getTinhTrang()));
        appointmentView.setAppointmentDate(event.getNgayKham());
        appointmentView.setAppointmentTime(event.getGioKham());

        appointmentView.setCreatedAt(LocalDateTime.now());
        appointmentView.setCreatedBy("appointments-read");

        appointmentsReadRepository.save(appointmentView);

    }

    @RabbitListener(
            queues = RabbitMQConfig.APPOINTMENT_UPDATED_QUEUE,
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handleAppointmentUpdated(AppointmentUpdatedEvent event) {
        Optional<AppointmentViewEntity> appointmentView = appointmentsReadRepository.findByAppointmentId(event.getAppointmentId());

        if (appointmentView.isPresent()) {
            AppointmentViewEntity view = appointmentView.get();
            view.setStatus(Status.valueOf(event.getStatus()));

            view.setUpdatedAt(LocalDateTime.now());
            view.setUpdatedBy("appointments-read");
            appointmentsReadRepository.save(view);
        }

    }

}

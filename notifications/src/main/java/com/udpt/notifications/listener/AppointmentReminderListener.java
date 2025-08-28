package com.udpt.notifications.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.notifications.config.RabbitMQConfig;
import com.udpt.notifications.dto.PrescriptionDetailDto;
import com.udpt.notifications.event.AppointmentReminderEvent;
import com.udpt.notifications.event.PrescriptionReadyEvent;
import com.udpt.notifications.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentReminderListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    public AppointmentReminderListener(final EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.REMINDER_QUEUE)
    public void consumeAppointmentReminder(String messageJson) throws JsonProcessingException {
        AppointmentReminderEvent event = objectMapper.readValue(messageJson, AppointmentReminderEvent.class);

        String subject = "Nhắc nhở lịch khám";
        String body = "<h3>Bác sĩ: " + event.getTenBacSi() + "</h2>" +
                "<p>Chuyên khoa: " + event.getChuyenKhoa() + "</h2>" +
                "<p>Bệnh nhân: " + event.getTenBenhNhan() + "</p>" +
                "<p>Ngày hẹn: " + event.getNgayHen() + "</p>";


        emailService.sendEmail(
                event.getEmailBenhNhan(),
                subject,
                body
        );
    }

}

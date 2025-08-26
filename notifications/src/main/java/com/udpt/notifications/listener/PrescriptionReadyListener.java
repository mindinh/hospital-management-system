package com.udpt.notifications.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.notifications.config.RabbitMQConfig;
import com.udpt.notifications.dto.PrescriptionDetailDto;
import com.udpt.notifications.event.PrescriptionReadyEvent;
import com.udpt.notifications.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionReadyListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    public PrescriptionReadyListener(final EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.PRESCRIPTION_QUEUE)
    public void consumePrescription(String messageJson) throws JsonProcessingException {
        PrescriptionReadyEvent event = objectMapper.readValue(messageJson, PrescriptionReadyEvent.class);

        String subject = "Đơn thuốc của bạn đã sẵn sàng";
        StringBuilder body = new StringBuilder();
        body.append("<h2>Bác sĩ: ").append(event.getTenBacSi()).append("</h2>");
        body.append("<p>Bệnh nhân: ").append(event.getTenBenhNhan()).append("</p>");
        body.append("<p>Ngày cấp: ").append(event.getNgayCap()).append("</p>");
        body.append("<h3>Chi tiết đơn thuốc:</h3><ul>");
        for (PrescriptionDetailDto med : event.getPrescriptionDetails()) {
            body.append("<li>")
                    .append(med.getTenThuoc())
                    .append(" - ").append(med.getSoLuong())
                    .append(" (").append(med.getChiDinh()).append(")")
                    .append("</li>");
        }
        body.append("</ul>");


        emailService.sendEmail(
                event.getEmailBenhNhan(),
                subject,
                body.toString()
        );
    }

}

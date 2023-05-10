package com.sehphirry.notification.service;

import com.sehphirry.notification.dto.NotificationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("{$fromEmail}")
    private String fromEmail;

    public void sendEmail(NotificationRequestDto notificationRequestDto) {
        log.info("Going to send email to {}", notificationRequestDto.getTo());
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(notificationRequestDto.getTo());
        msg.setSubject(notificationRequestDto.getSubject());
        msg.setText(notificationRequestDto.getBody());
        msg.setFrom(fromEmail);
        javaMailSender.send(msg);
    }

    public void sendSms(NotificationRequestDto notificationRequestDto) {
        log.info("Going to send sms to {}", notificationRequestDto.getTo());
    }
}

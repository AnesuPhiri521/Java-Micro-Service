package com.sehphirry.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sehphirry.notification.dto.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MQConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = {"${rabbitmq.queue.name.notification.queue}"})
    public void consumeMsg(String msg) throws JsonProcessingException {
        log.info("message received {}", msg);
        NotificationRequestDto notificationRequestDto = new ObjectMapper().readValue(msg, NotificationRequestDto.class);

        if (Objects.equals(notificationRequestDto.getAdditional1(), "EMAIL"))
            notificationService.sendEmail(notificationRequestDto);
        else if (Objects.equals(notificationRequestDto.getAdditional1(), "SMS"))
            notificationService.sendSms(notificationRequestDto);
    }
}

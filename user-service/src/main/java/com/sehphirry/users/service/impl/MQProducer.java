package com.sehphirry.users.service.impl;

import com.sehphirry.users.dto.request.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQProducer {

    @Value("${rabbitmq.queue.name.notification.queue}")
    private String notification_queue;

    @Value("${rabbitmq.queue.name.notification.exchange}")
    private String notification_exchange;

    @Value("${rabbitmq.queue.name.notification.routing.key}")
    private String notification_routing_key;

    public MQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(NotificationRequestDto msg){
        rabbitTemplate.convertAndSend(notification_exchange, notification_routing_key, msg);
    }
}

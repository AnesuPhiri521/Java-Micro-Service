package com.sehphirry.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name.notification.queue}")
    private String notification_queue;

    @Value("${rabbitmq.queue.name.notification.exchange}")
    private String notification_exchange;

    @Value("${rabbitmq.queue.name.notification.routing.key}")
    private String notification_routing_key;

    public Queue queue(){
        return new Queue(notification_queue);
    }

    public TopicExchange exchange(){
        return new TopicExchange(notification_exchange);
    }

    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(notification_routing_key);
    }
}

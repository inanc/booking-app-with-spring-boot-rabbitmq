package me.inanc.service;

import me.inanc.domain.Booking;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private final RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.messageExchange}")
    private String exchange;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendBooking(Booking booking, String routingKey) {
        rabbitTemplate.convertAndSend(exchange, routingKey, booking);
    }

    public void sendId(Long id, String routingKey) {
        rabbitTemplate.convertAndSend(exchange, routingKey, id);
    }

}

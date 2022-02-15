package me.inanc.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue}")
    private String bookingAddQueue;

    @Value("${spring.rabbitmq.queue2}")
    private String bookingEditQueue;

    @Value("${spring.rabbitmq.queue3}")
    private String bookingDeleteQueue;

    @Value("${spring.rabbitmq.queue4}")
    private String bookingAuditQueue;

    @Value("${spring.rabbitmq.messageExchange}")
    private String messageExchange;

    @Value("${spring.rabbitmq.bookingExchange}")
    private String bookingExchange;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;


    @Bean
    Queue bookingAddQueue() {
        return new Queue(bookingAddQueue, true);
    }

    @Bean
    Queue bookingEditQueue() {
        return new Queue(bookingEditQueue, true);
    }

    @Bean
    Queue bookingDeleteQueue() {
        return new Queue(bookingDeleteQueue, true);
    }

    @Bean
    Queue bookingAuditQueue() {
        return new Queue(bookingAuditQueue, true);
    }

    @Bean
    Exchange messageExchange() {
        return ExchangeBuilder.topicExchange(messageExchange).durable(true).build();
    }

    @Bean
    Exchange bookingExchange() {
        return ExchangeBuilder.topicExchange(bookingExchange).durable(true).build();
    }

    @Bean
    public Declarables bindings() {
        return new Declarables(
                BindingBuilder
                        .bind(bookingAddQueue())
                        .to(bookingExchange())
                        .with("#.add")
                        .noargs(),
                BindingBuilder
                        .bind(bookingEditQueue())
                        .to(bookingExchange())
                        .with("#.edit")
                        .noargs(),
                BindingBuilder
                        .bind(bookingDeleteQueue())
                        .to(bookingExchange())
                        .with("#.delete")
                        .noargs(),
                BindingBuilder
                        .bind(bookingAuditQueue())
                        .to(messageExchange())
                        .with("audit")
                        .noargs(),
                BindingBuilder
                        .bind(bookingExchange())
                        .to(messageExchange())
                        .with("booking.*")
                        .noargs()
        );
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host, port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

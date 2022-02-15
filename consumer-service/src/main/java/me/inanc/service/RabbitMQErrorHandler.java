package me.inanc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@RequiredArgsConstructor
@Slf4j
@Service
public class RabbitMQErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        System.out.println("======================================================================================");
        System.out.println("error occurred in message listener and handled in error handler" + t.toString());
        System.out.println("======================================================================================");

        throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", t);

    }
}
package me.inanc.service;

import lombok.extern.slf4j.Slf4j;
import me.inanc.domain.Booking;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class RabbitMqConsumer implements RabbitListenerConfigurer {

    @Autowired
    BookingService bookingService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void addQueueListener(Booking booking) {

        Booking saved = bookingService.save(booking);
        log.info("added booking.. " + saved);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue2}")
    public void editQueueListener(Booking booking) {
        if (booking.getId() != null)
            bookingService.update(booking);

    }

    @RabbitListener(queues = "${spring.rabbitmq.queue3}")
    public void deleteQueueListener(Long id) {
        bookingService.delete(id);

        log.info("delete booking.. " + id);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue4}")
    public void auditQueueListener(Long id) {
        Optional<Booking> optionalBooking = bookingService.findById(id);
        optionalBooking.ifPresent(booking -> {
            log.info("audit booking.. " + booking);
        });

    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

}

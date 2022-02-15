package me.inanc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.inanc.domain.Booking;
import me.inanc.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/booking")
@Api(value = "Booking Api documentation")
public class BookingProducerController {

    private final RabbitMqSender rabbitMqSender;
    @Value("${app.message}")
    private String message;

    @Autowired
    public BookingProducerController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Booking auditing method")
    public String audit(@PathVariable Long id) {
        log.info("booking: audit send rabbitmq: " + id);
        rabbitMqSender.sendId(id, "audit");
        return message;
    }

    @PostMapping(value = "")
    @ApiOperation(value = "New Booking adding method")
    public String add(@RequestBody Booking booking) {
        log.info("booking:add send rabbitmq: " + booking);
        rabbitMqSender.sendBooking(booking, "booking.add");
        return message;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Booking editing method")
    public String edit(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setId(id);
        log.info("booking: edit send rabbitmq: " + booking);
        rabbitMqSender.sendBooking(booking, "booking.edit");
        return message;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Booking deleting method")
    public String delete(@PathVariable Long id) {
        log.info("booking: delete send rabbitmq: " + id);
        rabbitMqSender.sendId(id, "booking.delete");
        return message;
    }
}

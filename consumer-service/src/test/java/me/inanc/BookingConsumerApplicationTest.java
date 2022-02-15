package me.inanc;

import org.junit.Test;
import org.junit.runner.Runner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BookingConsumerApplicationTest {


    @MockBean
    private Runner runner;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Receiver receiver;

    @Test
    public void test() throws Exception {
//        try {
//            rabbitTemplate.convertAndSend("add.queue",
//                    "Hello from RabbitMQ!");
//            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        }
//        catch (AmqpConnectException e) {
//            // ignore - rabbit is not running
//        }

    }
}
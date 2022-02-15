package me.inanc;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableRabbit
@SpringBootApplication
@EnableJpaAuditing
public class BookingConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingConsumerApplication.class, args);
	}
}

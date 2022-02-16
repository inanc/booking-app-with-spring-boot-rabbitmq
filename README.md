# booking-app-with-spring-boot-rabbitmq
This project is a simple booking provider and consumer services developed with Spring Boot, Spring Data, RabbitMQ, REST API, Swagger, H2 Database
## Getting started

First, clone this repository.

Then, build it locally with:

```shell
mvn clean install
```

You can run the app in a command line with:

```shell
mvn spring-boot:run
```

## API documentation

* Swagger UI: http://localhost:9091/swagger-ui.html

## Run RabbitMQ locally
```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```

* http://localhost:15672/
* User: guest
* Password: guest


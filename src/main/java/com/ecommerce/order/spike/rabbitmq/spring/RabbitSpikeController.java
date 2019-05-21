package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/spike/rabbitmq")
public class RabbitSpikeController {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private AmqpTemplate amqpTemplate;

    public RabbitSpikeController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping(value = "/created/sendto/{exchange}")
    public void sendCreated(@PathVariable(name = "exchange") String exchange) {
        amqpTemplate.convertAndSend(exchange, "order.created", new OrderCreatedEvent("1234", new Random().nextDouble()));
    }

    @GetMapping(value = "/updated/sendto/{exchange}")
    public void sendUpdated(@PathVariable(name = "exchange") String exchange) {
        amqpTemplate.convertAndSend(exchange, "order.updated", new OrderUpdatedEvent("1234", new Random().nextDouble()));
    }


}

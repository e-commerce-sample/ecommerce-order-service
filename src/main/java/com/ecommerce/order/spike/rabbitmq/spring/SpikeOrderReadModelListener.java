package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RabbitListener(queues = "order-summary-queue", errorHandler = "rabbitListenerErrorHandler")
public class SpikeOrderReadModelListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    @RabbitHandler
    public void handleOrderCreated(OrderCreatedEvent orderCreatedEvent) {
        logger.info("handle orderCreatedEvent:{}", orderCreatedEvent.getPrice());
    }

    @RabbitHandler
    public void handleOrderUpdateEvent(OrderUpdatedEvent orderUpdatedEvent) {
        if (new Random().nextBoolean()) {
            logger.info("handle orderUpdatedEvent:{}", orderUpdatedEvent.getUpdatedPrice());
        } else {
            throw new RuntimeException("handle order created event failed.");
        }
    }


}

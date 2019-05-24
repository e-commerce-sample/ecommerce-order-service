package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.order.order.model.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "order-notification-queue")
public class SpikeOrderNotificationListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    @RabbitHandler
    public void handleOrderCreated(OrderCreatedEvent orderCreatedEvent) {
        logger.info("handle orderCreatedEvent:{}", orderCreatedEvent.get_id());
    }
}

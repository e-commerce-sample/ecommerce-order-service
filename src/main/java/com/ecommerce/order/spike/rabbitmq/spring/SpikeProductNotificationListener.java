package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.order.product.ProductCreatedEvent;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "product-notification-queue")
public class SpikeProductNotificationListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    @RabbitHandler
    public void handleProductCreated(ProductCreatedEvent ev) {
        logger.info("handle productCreatedEvent:{}", ev.get_id());
    }
}

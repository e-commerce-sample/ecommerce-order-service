package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.order.order.model.event.OrderAddressChangedEvent;
import com.ecommerce.order.order.model.event.OrderCreatedEvent;
import com.ecommerce.order.order.model.event.OrderPaidEvent;
import com.ecommerce.order.order.model.event.OrderProductChangedEvent;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "order-summary-queue")
public class SpikeOrderReadModelListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    @RabbitHandler
    public void handleOrderCreated(OrderCreatedEvent event) {
        logger.info("handle orderCreatedEvent:{}", event.getPrice());
    }

    @RabbitHandler
    public void handleOrderAddressChanged(OrderAddressChangedEvent event) {
        logger.info("handle orderAddressChangedEvent:{}", event.getOrderId());
    }

    @RabbitHandler
    public void handleOrderProductChanged(OrderProductChangedEvent event) {
        logger.info("handle orderProductChangedEvent:{}", event.getOrderId());
    }

    @RabbitHandler
    public void handleOrderPaid(OrderPaidEvent event) {
        logger.info("handle orderPaidEvent:{}", event.getOrderId());
    }


}

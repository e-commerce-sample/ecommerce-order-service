package com.ecommerce.order.order;

import com.ecommerce.common.event.order.OrderEvent;
import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.ecommerce.order.order.OrderRabbitmqConfig.ORDER_RECEIVE_QUEUE;
import static com.ecommerce.order.order.model.OrderId.orderId;

@Component
@RabbitListener(queues = ORDER_RECEIVE_QUEUE)
public class OrderRabbitListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();
    private final OrderRepresentationService orderRepresentationService;

    public OrderRabbitListener(OrderRepresentationService orderRepresentationService) {
        this.orderRepresentationService = orderRepresentationService;
    }

    @RabbitHandler
    public void on(OrderEvent event) {
        orderRepresentationService.cqrsSync(orderId(event.getOrderId()));
    }

}

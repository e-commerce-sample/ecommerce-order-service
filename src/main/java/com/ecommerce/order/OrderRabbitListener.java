package com.ecommerce.order;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.order.OrderEvent;
import com.ecommerce.order.order.OrderEventHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class OrderRabbitListener {
    private OrderEventHandler eventHandler;

    public OrderRabbitListener(OrderEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @NewSpan("CQRS sync according to order creation")
    @RabbitHandler
    public void on(OrderEvent event) {
        eventHandler.cqrsSync(event);
    }

}

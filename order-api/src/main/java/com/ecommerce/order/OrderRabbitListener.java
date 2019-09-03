package com.ecommerce.order;

import com.ecommerce.order.event.OrderEvent;
import com.ecommerce.order.order.OrderEventHandler;
import com.ecommerce.spring.common.event.consume.EcommerceRabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class OrderRabbitListener {
    private OrderEventHandler eventHandler;

    public OrderRabbitListener(OrderEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @RabbitHandler
    public void on(OrderEvent event) {
        eventHandler.cqrsSync(event);
    }

}

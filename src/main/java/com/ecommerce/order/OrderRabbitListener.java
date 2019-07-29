package com.ecommerce.order;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.order.OrderEvent;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

import static com.ecommerce.order.order.model.OrderId.of;

@Component
@EcommerceRabbitListener
public class OrderRabbitListener {
    private final OrderRepresentationService orderRepresentationService;

    public OrderRabbitListener(OrderRepresentationService orderRepresentationService) {
        this.orderRepresentationService = orderRepresentationService;
    }

    @RabbitHandler
    public void on(OrderEvent event) {
        orderRepresentationService.cqrsSync(of(event.getOrderId()));
    }

}

package com.ecommerce.order.order;

import com.ecommerce.order.event.OrderEvent;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import org.springframework.stereotype.Component;

import static com.ecommerce.order.order.model.OrderId.of;

@Component
public class OrderEventHandler {
    private final OrderRepresentationService orderRepresentationService;

    public OrderEventHandler(OrderRepresentationService orderRepresentationService) {
        this.orderRepresentationService = orderRepresentationService;
    }

    public void cqrsSync(OrderEvent event) {
        orderRepresentationService.cqrsSync(of(event.getOrderId()));
    }
}

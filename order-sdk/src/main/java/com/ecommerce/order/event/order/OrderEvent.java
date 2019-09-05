package com.ecommerce.order.event.order;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Getter;

@Getter
public abstract class OrderEvent extends DomainEvent {
    private String orderId;

    @Deprecated
    protected OrderEvent() {
    }

    OrderEvent(String orderId) {
        super("Order");
        this.orderId = orderId;
    }
}

package com.ecommerce.order.event.order;

import com.ecommerce.shared.event.DomainEvent;

public abstract class OrderEvent extends DomainEvent {
    private String orderId;

    protected OrderEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

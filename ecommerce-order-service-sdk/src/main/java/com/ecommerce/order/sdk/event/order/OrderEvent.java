package com.ecommerce.order.sdk.event.order;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Getter;

@Getter
public abstract class OrderEvent extends DomainEvent {
    private String orderId;

    protected OrderEvent(String orderId) {
        this.orderId = orderId;
    }
}

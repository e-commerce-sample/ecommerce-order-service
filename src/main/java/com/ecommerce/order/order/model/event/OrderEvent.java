package com.ecommerce.order.order.model.event;

import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.event.DomainEventType;
import com.ecommerce.order.order.model.OrderId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public abstract class OrderEvent extends DomainEvent {
    private final OrderId orderId;

    @JsonCreator
    protected OrderEvent(@JsonProperty("orderId") OrderId orderId,
                         @JsonProperty("_id") String _id,
                         @JsonProperty("_type") DomainEventType _type,
                         @JsonProperty("_createdAt") Instant _createdAt) {
        super(_id, _type, _createdAt);
        this.orderId = orderId;
    }

    protected OrderEvent(OrderId orderId, DomainEventType eventType) {
        super(eventType);
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}

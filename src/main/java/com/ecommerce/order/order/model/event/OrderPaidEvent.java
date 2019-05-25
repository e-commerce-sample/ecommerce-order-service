package com.ecommerce.order.order.model.event;

import com.ecommerce.order.common.event.DomainEventType;
import com.ecommerce.order.order.model.OrderId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

import static com.ecommerce.order.common.event.DomainEventType.ORDER_PAID;

public class OrderPaidEvent extends OrderEvent {
    @JsonCreator
    private OrderPaidEvent(@JsonProperty("_id") String _id,
                           @JsonProperty("_type") DomainEventType _type,
                           @JsonProperty("_createdAt") Instant _createdAt,
                           @JsonProperty("orderId") OrderId orderId) {
        super(orderId, _id, _type, _createdAt);
    }

    public OrderPaidEvent(OrderId orderId) {
        super(orderId, ORDER_PAID);
    }

}

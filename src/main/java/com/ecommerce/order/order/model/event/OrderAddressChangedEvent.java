package com.ecommerce.order.order.model.event;

import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.event.DomainEventType;
import com.ecommerce.order.order.model.OrderId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

import static com.ecommerce.order.common.event.DomainEventType.ORDER_ADDRESS_CHANGED;

public class OrderAddressChangedEvent extends DomainEvent {
    private final OrderId orderId;
    private final String oldAddress;
    private final String newAddress;

    @JsonCreator
    private OrderAddressChangedEvent(@JsonProperty("_id") String _id,
                                     @JsonProperty("_type") DomainEventType _type,
                                     @JsonProperty("_createdAt") Instant _createdAt,
                                     @JsonProperty("orderId") OrderId orderId,
                                     @JsonProperty("oldAddress") String oldAddress,
                                     @JsonProperty("newAddress") String newAddress) {
        super(_id, _type, _createdAt);
        this.orderId = orderId;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    public OrderAddressChangedEvent(OrderId orderId, String oldAddress, String newAddress) {
        super(ORDER_ADDRESS_CHANGED);
        this.orderId = orderId;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public String getNewAddress() {
        return newAddress;
    }
}

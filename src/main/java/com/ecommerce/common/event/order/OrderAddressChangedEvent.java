package com.ecommerce.common.event.order;

import com.ecommerce.common.event.DomainEventType;
import com.ecommerce.order.order.model.OrderId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

import static com.ecommerce.common.event.DomainEventType.ORDER_ADDRESS_CHANGED;

public class OrderAddressChangedEvent extends OrderEvent {
    private final String oldAddress;
    private final String newAddress;

    @JsonCreator
    private OrderAddressChangedEvent(@JsonProperty("_id") String _id,
                                     @JsonProperty("_type") DomainEventType _type,
                                     @JsonProperty("_createdAt") Instant _createdAt,
                                     @JsonProperty("orderId") OrderId orderId,
                                     @JsonProperty("oldAddress") String oldAddress,
                                     @JsonProperty("newAddress") String newAddress) {
        super(orderId, _id, _type, _createdAt);
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    public OrderAddressChangedEvent(OrderId orderId, String oldAddress, String newAddress) {
        super(orderId, ORDER_ADDRESS_CHANGED);
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public String getNewAddress() {
        return newAddress;
    }
}

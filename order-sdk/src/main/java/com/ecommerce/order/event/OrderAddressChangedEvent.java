package com.ecommerce.order.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderAddressChangedEvent extends OrderEvent {
    private String oldAddress;
    private String newAddress;

    @JsonCreator
    public OrderAddressChangedEvent(@JsonProperty("orderId") String orderId,
                                    @JsonProperty("oldAddress") String oldAddress,
                                    @JsonProperty("newAddress") String newAddress) {
        super(orderId);
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

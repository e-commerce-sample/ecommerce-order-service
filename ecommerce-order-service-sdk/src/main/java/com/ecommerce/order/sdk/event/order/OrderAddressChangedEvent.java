package com.ecommerce.order.sdk.event.order;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class OrderAddressChangedEvent extends OrderEvent {
    private String oldAddress;
    private String newAddress;

    @ConstructorProperties({"orderId", "oldAddress", "newAddress"})
    public OrderAddressChangedEvent(String orderId, String oldAddress, String newAddress) {
        super(orderId);
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }
}

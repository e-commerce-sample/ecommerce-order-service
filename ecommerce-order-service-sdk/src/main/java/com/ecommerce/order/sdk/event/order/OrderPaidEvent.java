package com.ecommerce.order.sdk.event.order;

import lombok.Getter;

import java.beans.ConstructorProperties;


@Getter
public class OrderPaidEvent extends OrderEvent {

    @ConstructorProperties({"orderId"})
    public OrderPaidEvent(String orderId) {
        super(orderId);
    }
}

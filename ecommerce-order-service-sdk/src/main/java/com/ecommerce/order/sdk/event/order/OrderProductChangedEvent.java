package com.ecommerce.order.sdk.event.order;

import lombok.Getter;

import java.beans.ConstructorProperties;


@Getter
public class OrderProductChangedEvent extends OrderEvent {
    private String productId;
    private int originCount;
    private int newCount;

    @ConstructorProperties({"orderId", "productId", "originCount", "newCount"})
    public OrderProductChangedEvent(String orderId, String productId, int originCount, int newCount) {
        super(orderId);
        this.productId = productId;
        this.originCount = originCount;
        this.newCount = newCount;
    }
}

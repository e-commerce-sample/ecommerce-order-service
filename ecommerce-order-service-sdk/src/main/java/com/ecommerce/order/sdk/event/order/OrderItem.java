package com.ecommerce.order.sdk.event.order;

import lombok.Value;

@Value
public class OrderItem {
    private String productId;
    private int count;
}

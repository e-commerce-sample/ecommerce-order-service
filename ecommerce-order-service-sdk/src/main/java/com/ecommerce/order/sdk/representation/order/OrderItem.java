package com.ecommerce.order.sdk.representation.order;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderItem {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;
}

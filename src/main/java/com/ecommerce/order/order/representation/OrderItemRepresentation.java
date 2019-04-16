package com.ecommerce.order.order.representation;

import java.math.BigDecimal;

public class OrderItemRepresentation {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;

    public OrderItemRepresentation(String productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }
}

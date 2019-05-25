package com.ecommerce.order.order.representation.detail;

import com.ecommerce.order.common.ddd.Representation;

import java.math.BigDecimal;

public class OrderItemRepresentation implements Representation {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;

    public OrderItemRepresentation(String productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}

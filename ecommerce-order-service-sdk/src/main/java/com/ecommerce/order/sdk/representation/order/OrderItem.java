package com.ecommerce.order.sdk.representation.order;

import java.math.BigDecimal;

public class OrderItem {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;

    public OrderItem(String productId, int count, BigDecimal itemPrice) {
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

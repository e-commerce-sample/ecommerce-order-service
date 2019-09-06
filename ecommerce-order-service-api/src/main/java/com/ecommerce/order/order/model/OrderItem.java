package com.ecommerce.order.order.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {
    private String productId;
    private int count;
    private BigDecimal itemPrice;

    private OrderItem(String productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public static OrderItem create(String productId, int count, BigDecimal itemPrice) {
        return new OrderItem(productId, count, itemPrice);
    }

    BigDecimal totalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(count));
    }

    public void updateCount(int count) {
        this.count = count;
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

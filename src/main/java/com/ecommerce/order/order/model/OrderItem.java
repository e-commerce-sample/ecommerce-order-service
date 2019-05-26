package com.ecommerce.order.order.model;

import java.math.BigDecimal;

public class OrderItem {
    private ProductId productId;
    private int count;
    private BigDecimal itemPrice;

    private OrderItem() {
    }

    private OrderItem(ProductId productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public static OrderItem create(ProductId productId, int count, BigDecimal itemPrice) {
        return new OrderItem(productId, count, itemPrice);
    }

    BigDecimal totalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(count));
    }

    public void updateCount(int count) {
        this.count = count;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}

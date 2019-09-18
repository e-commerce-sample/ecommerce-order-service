package com.ecommerce.order.sdk.representation.order;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderRepresentation {
    private final String id;
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;
    private final String status;
    private final Instant createdAt;

    public OrderRepresentation(String id,
                               List<OrderItem> items,
                               BigDecimal totalPrice,
                               String status,
                               Instant createdAt) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}

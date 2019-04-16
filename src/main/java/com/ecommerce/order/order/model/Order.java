package com.ecommerce.order.order.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.ecommerce.order.order.model.OrderId.newOrderId;
import static com.ecommerce.order.order.model.OrderStatus.CREATED;
import static com.google.common.collect.Lists.newArrayList;
import static java.time.Instant.now;

public class Order {
    private OrderId id;
    private List<OrderItem> items = newArrayList();
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Instant createdAt;

    private Order() {
    }

    private Order(List<OrderItem> items) {
        this.id = newOrderId();
        this.items.addAll(items);
        this.totalPrice = calculateTotalPrice();
        this.status = CREATED;
        this.createdAt = now();
    }

    public static Order create(List<OrderItem> items) {
        return new Order(items);
    }

    private BigDecimal calculateTotalPrice() {
        return BigDecimal.valueOf(12);
    }

    public OrderId getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}

package com.ecommerce.order.order.representation;

import com.ecommerce.order.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderRepresentation {
    private final String orderId;
    private final List<OrderItemRepresentation> items;
    private final BigDecimal totalPrice;
    private final OrderStatus status;
    private final Instant createdAt;

    public OrderRepresentation(String orderId,
                               List<OrderItemRepresentation> items,
                               BigDecimal totalPrice,
                               OrderStatus status,
                               Instant createdAt) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }
}

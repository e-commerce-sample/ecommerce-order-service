package com.ecommerce.order.order.representation.summary;

import com.ecommerce.order.common.ddd.Representation;
import com.ecommerce.order.common.utils.Address;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderSummaryRepresentation implements Representation {
    private String id;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Instant createdAt;
    private Address address;

    private OrderSummaryRepresentation() {
    }

    private OrderSummaryRepresentation(String id,
                                       BigDecimal totalPrice,
                                       OrderStatus status,
                                       Instant createdAt,
                                       Address address) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.address = address;
    }

    public static OrderSummaryRepresentation from(Order order) {
        return new OrderSummaryRepresentation(order.getId().toString(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getAddress());
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Address getAddress() {
        return address;
    }
}

package com.ecommerce.order.sdk.event.order;

import com.ecommerce.shared.model.Address;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Getter
public class OrderCreatedEvent extends OrderEvent {
    private BigDecimal price;
    private Address address;
    private List<OrderItem> items;
    private Instant createdAt;

    @ConstructorProperties({"orderId", "price", "address", "items", "createdAt"})
    public OrderCreatedEvent(String orderId, BigDecimal price, Address address, List<OrderItem> items, Instant createdAt) {
        super(orderId);
        this.price = price;
        this.address = address;
        this.items = items;
        this.createdAt = createdAt;
    }
}

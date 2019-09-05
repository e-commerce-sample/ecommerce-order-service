package com.ecommerce.order.event.order;

import com.ecommerce.shared.model.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreatedEvent extends OrderEvent {
    private BigDecimal price;
    private Address address;
    private List<OrderItem> items;
    private Instant createdAt;


    public OrderCreatedEvent(String orderId,
                             BigDecimal price,
                             Address address,
                             Instant createdAt,
                             List<OrderItem> items) {
        super(orderId);
        this.price = price;
        this.createdAt = createdAt;
        this.address = address;
        this.items = items;
    }

}

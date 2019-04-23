package com.ecommerce.order.order.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory {
    private final OrderIdGenerator idGenerator;

    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(List<OrderItem> items) {
        return Order.create(idGenerator.generate(), items);
    }
}

package com.ecommerce.order.spike.rabbitmq.spring;

import static com.ecommerce.order.spike.rabbitmq.spring.EventType.ORDER_CREATED;

public class OrderCreatedEvent extends DomainEvent {
    private String orderId;
    private double price;

    private OrderCreatedEvent() {
        super(ORDER_CREATED);
    }

    public OrderCreatedEvent(String orderId, double price) {
        this();
        this.orderId = orderId;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getPrice() {
        return price;
    }
}

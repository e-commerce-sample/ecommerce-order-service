package com.ecommerce.order.spike.rabbitmq.spring;

import static com.ecommerce.order.spike.rabbitmq.spring.EventType.ORDER_UPDATED;

public class OrderUpdatedEvent extends DomainEvent {
    private String orderId;
    private double updatedPrice;

    private OrderUpdatedEvent() {
        super(ORDER_UPDATED);
    }

    public OrderUpdatedEvent(String orderId, double updatedPrice) {
        this();
        this.orderId = orderId;
        this.updatedPrice = updatedPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getUpdatedPrice() {
        return updatedPrice;
    }
}

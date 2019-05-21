package com.ecommerce.order.spike.rabbitmq.spring;

public class OrderUpdatedEvent {
    private String orderId;
    private double updatedPrice;

    private OrderUpdatedEvent() {
    }

    public OrderUpdatedEvent(String orderId, double updatedPrice) {
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

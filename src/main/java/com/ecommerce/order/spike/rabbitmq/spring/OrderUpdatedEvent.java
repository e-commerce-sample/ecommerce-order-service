package com.ecommerce.order.spike.rabbitmq.spring;

public class OrderUpdatedEvent {
    private String orderId;
    private double price;

    private OrderUpdatedEvent() {
    }

    public OrderUpdatedEvent(String orderId, double price) {
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

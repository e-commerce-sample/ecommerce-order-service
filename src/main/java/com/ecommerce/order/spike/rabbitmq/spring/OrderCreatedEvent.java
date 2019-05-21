package com.ecommerce.order.spike.rabbitmq.spring;

public class OrderCreatedEvent {
    private String orderId;
    private double price;

    private OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId, double price) {
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

package com.ecommerce.order.spike.rabbitmq.spring;

public enum EventType {
    ORDER_CREATED("order.order.created"),
    ORDER_UPDATED("order.order.created");

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

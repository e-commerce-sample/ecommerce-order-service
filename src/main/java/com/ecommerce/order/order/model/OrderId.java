package com.ecommerce.order.order.model;

public class OrderId {
    private String id;

    public OrderId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}

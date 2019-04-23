package com.ecommerce.order.order.model;

import com.ecommerce.order.common.ddd.Identity;

import java.util.Objects;

public class OrderId implements Identity {
    private String id;

    private OrderId() {
    }

    private OrderId(String id) {
        this.id = id;
    }

    public static OrderId orderId(String id) {
        return new OrderId(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderId orderId = (OrderId) o;
        return Objects.equals(id, orderId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.ecommerce.order.order.model;

import java.util.Objects;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;

public class OrderId {
    private String id;

    private OrderId() {
    }

    private OrderId(String id) {
        this.id = id;
    }

    public static OrderId orderId(String id) {
        return new OrderId(id);
    }

    public static OrderId newOrderId() {
        return new OrderId(newUuid());
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

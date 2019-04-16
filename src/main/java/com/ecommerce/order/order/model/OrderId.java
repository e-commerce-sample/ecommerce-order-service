package com.ecommerce.order.order.model;

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
}

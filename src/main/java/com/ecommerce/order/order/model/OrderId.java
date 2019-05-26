package com.ecommerce.order.order.model;

import com.ecommerce.common.model.AbstractId;
import com.ecommerce.common.utils.UuidGenerator;

public class OrderId extends AbstractId {
    private OrderId() {
    }

    private OrderId(String id) {
        super(id);
    }

    public static OrderId orderId(String id) {
        return new OrderId(id);
    }

    public static OrderId newOrderId() {
        return orderId(UuidGenerator.newUuid());
    }

}

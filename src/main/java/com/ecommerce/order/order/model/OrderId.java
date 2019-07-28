package com.ecommerce.order.order.model;

import com.ecommerce.common.model.AbstractId;
import com.ecommerce.common.utils.UuidGenerator;

public class OrderId extends AbstractId {
    private OrderId() {
    }

    private OrderId(String id) {
        super(id);
    }

    public static OrderId of(String id) {
        return new OrderId(id);
    }

    public static OrderId newId() {
        return of(UuidGenerator.newUuid());
    }

}

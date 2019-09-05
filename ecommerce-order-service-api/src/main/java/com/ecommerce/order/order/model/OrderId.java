package com.ecommerce.order.order.model;

import com.ecommerce.shared.model.AbstractId;
import com.ecommerce.shared.utils.UuidGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderId extends AbstractId {

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

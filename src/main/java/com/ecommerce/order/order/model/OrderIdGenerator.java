package com.ecommerce.order.order.model;

import com.ecommerce.common.utils.UuidGenerator;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {

    public OrderId generate() {
        return OrderId.of(UuidGenerator.newUuid());
    }
}

package com.ecommerce.order.order.model;

import com.ecommerce.order.common.ddd.DomainService;
import com.ecommerce.order.common.utils.UuidGenerator;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator implements DomainService {

    public OrderId generate() {
        return OrderId.orderId(UuidGenerator.newUuid());
    }
}

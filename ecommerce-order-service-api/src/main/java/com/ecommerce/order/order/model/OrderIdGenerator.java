package com.ecommerce.order.order.model;

import com.ecommerce.shared.utils.UuidGenerator;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {

    public String generate() {
        return UuidGenerator.newUuid();
    }
}

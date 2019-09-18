package com.ecommerce.order.sdk.representation.order;


import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Value
public class OrderRepresentation {
    private final String id;
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;
    private final String status;
    private final Instant createdAt;

}

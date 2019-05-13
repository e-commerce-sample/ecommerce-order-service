package com.ecommerce.order.order.representation;

import com.ecommerce.order.common.ddd.Representation;
import com.ecommerce.order.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

// TODO: 2019-05-14 use rabbitmq and cqrs 
public class OrderSummaryRepresentation implements Representation {
    private final String id;
    private final BigDecimal totalPrice;
    private final OrderStatus status;
    private final Instant createdAt;

    public OrderSummaryRepresentation(String id,
                                      List<OrderItemRepresentation> items,
                                      BigDecimal totalPrice,
                                      OrderStatus status,
                                      Instant createdAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }
}

package com.ecommerce.order.order.representation;

import com.ecommerce.order.common.ddd.Representation;
import com.ecommerce.order.common.utils.Address;
import com.ecommerce.order.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

// TODO: 2019-05-14 use rabbitmq and cqrs,order created ; order address changed; order product count changed ; order paid
public class OrderSummaryRepresentation implements Representation {
    private final String id;
    private final BigDecimal totalPrice;
    private final OrderStatus status;
    private final Instant createdAt;
    private final Address address;

    public OrderSummaryRepresentation(String id,
                                      BigDecimal totalPrice,
                                      OrderStatus status,
                                      Instant createdAt,
                                      Address address) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.address = address;
    }
}

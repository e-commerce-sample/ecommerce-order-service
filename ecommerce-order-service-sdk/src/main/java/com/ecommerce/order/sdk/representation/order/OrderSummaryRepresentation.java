package com.ecommerce.order.sdk.representation.order;

import com.ecommerce.shared.model.Address;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class OrderSummaryRepresentation {
    private String id;
    private BigDecimal totalPrice;
    private String status;
    private Instant createdAt;
    private Address address;
}

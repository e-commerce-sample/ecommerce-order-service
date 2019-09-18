package com.ecommerce.order.sdk.representation.order.summary;

import com.ecommerce.shared.model.Address;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderSummaryRepresentation {
    private String id;
    private BigDecimal totalPrice;
    private String status;
    private Instant createdAt;
    private Address address;

    private OrderSummaryRepresentation() {
    }

    public OrderSummaryRepresentation(String id,
                                      BigDecimal totalPrice,
                                      String status,
                                      Instant createdAt,
                                      Address address) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Address getAddress() {
        return address;
    }
}

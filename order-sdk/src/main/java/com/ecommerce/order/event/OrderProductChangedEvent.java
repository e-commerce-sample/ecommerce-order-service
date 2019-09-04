package com.ecommerce.order.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderProductChangedEvent extends OrderEvent {
    private String productId;
    private int originCount;
    private int newCount;

    @JsonCreator
    public OrderProductChangedEvent(@JsonProperty("orderId") String orderId,
                                    @JsonProperty("productId") String productId,
                                    @JsonProperty("originCount") int originCount,
                                    @JsonProperty("newCount") int newCount) {
        super(orderId);
        this.productId = productId;
        this.originCount = originCount;
        this.newCount = newCount;
    }

    public String getProductId() {
        return productId;
    }

    public int getOriginCount() {
        return originCount;
    }

    public int getNewCount() {
        return newCount;
    }
}

package com.ecommerce.order.event.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
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

}

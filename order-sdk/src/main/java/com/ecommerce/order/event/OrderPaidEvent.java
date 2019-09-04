package com.ecommerce.order.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPaidEvent extends OrderEvent {

    @JsonCreator
    public OrderPaidEvent(@JsonProperty("orderId") String orderId) {
        super(orderId);
    }

}

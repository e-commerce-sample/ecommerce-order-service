package com.ecommerce.order.event.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class OrderPaidEvent extends OrderEvent {

    @JsonCreator
    public OrderPaidEvent(@JsonProperty("orderId") String orderId) {
        super(orderId);
    }


}

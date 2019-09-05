package com.ecommerce.order.event.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OrderAddressChangedEvent extends OrderEvent {
    private String oldAddress;
    private String newAddress;

    @JsonCreator
    public OrderAddressChangedEvent(@JsonProperty("orderId") String orderId,
                                    @JsonProperty("oldAddress") String oldAddress,
                                    @JsonProperty("newAddress") String newAddress) {
        super(orderId);
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

}

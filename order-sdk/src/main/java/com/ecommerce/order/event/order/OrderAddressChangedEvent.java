package com.ecommerce.order.event.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderAddressChangedEvent extends OrderEvent {
    private String oldAddress;
    private String newAddress;

    public OrderAddressChangedEvent(String orderId,
                                    String oldAddress,
                                    String newAddress) {
        super(orderId);
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

}

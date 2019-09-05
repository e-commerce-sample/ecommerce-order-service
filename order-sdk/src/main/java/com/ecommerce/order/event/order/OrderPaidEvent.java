package com.ecommerce.order.event.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(String orderId) {
        super(orderId);
    }


}

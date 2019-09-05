package com.ecommerce.order.event.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProductChangedEvent extends OrderEvent {
    private String productId;
    private int originCount;
    private int newCount;

    public OrderProductChangedEvent(String orderId,
                                    String productId,
                                    int originCount,
                                    int newCount) {
        super(orderId);
        this.productId = productId;
        this.originCount = originCount;
        this.newCount = newCount;
    }

}

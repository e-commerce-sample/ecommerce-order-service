package com.ecommerce.order.common.event;

import static com.ecommerce.order.order.OrderRabbitmqConfig.ORDER_PUBLISH_EXCHANGE;
import static com.ecommerce.order.product.ProductRabbitmqConfig.PRODUCT_PUBLISH_EXCHANGE;

public enum DomainEventType {
    ORDER_CREATED(ORDER_PUBLISH_EXCHANGE),
    ORDER_ADDRESS_CHANGED(ORDER_PUBLISH_EXCHANGE),
    ORDER_PAID(ORDER_PUBLISH_EXCHANGE),
    ORDER_PRODUCT_CHANGED(ORDER_PUBLISH_EXCHANGE),
    PRODUCT_CREATED(PRODUCT_PUBLISH_EXCHANGE);

    private final String exchange;

    DomainEventType(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }
}

package com.ecommerce.order.common.event;

public enum EventType {
    ORDER_CREATED("order", "created"),
    ORDER_ADDRESS_CHANGED("order", "address-changed"),
    ORDER_PAID("order", "paid"),
    ORDER_PRODUCT_CHANGED("order", "product-changed"),
    PRODUCT_CREATED("product", "created");

    private final String type;
    private final String aggregate;

    EventType(String aggregate, String type) {
        this.aggregate = aggregate;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getAggregate() {
        return aggregate;
    }

}

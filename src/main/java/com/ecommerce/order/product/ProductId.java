package com.ecommerce.order.product;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;

public class ProductId {
    private String id;

    private ProductId() {
    }

    private ProductId(String id) {
        this.id = id;
    }

    public static ProductId productId(String id) {
        return new ProductId(id);
    }

    public static ProductId newProductId() {
        return new ProductId(newUuid());
    }

    @Override
    public String toString() {
        return id;
    }
}

package com.ecommerce.order.order.model;

import com.ecommerce.common.model.AbstractId;

import static com.ecommerce.common.utils.UuidGenerator.newUuid;

public class ProductId extends AbstractId {

    private ProductId() {
    }

    private ProductId(String id) {
        super(id);
    }

    public static ProductId productId(String id) {
        return new ProductId(id);
    }

    public static ProductId newProductId() {
        return new ProductId(newUuid());
    }


}

package com.ecommerce.order.product;

import com.ecommerce.order.common.model.AbstractId;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;

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

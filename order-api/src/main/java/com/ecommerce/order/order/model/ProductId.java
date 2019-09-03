package com.ecommerce.order.order.model;

import com.ecommerce.shared.model.AbstractId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.ecommerce.shared.utils.UuidGenerator.newUuid;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductId extends AbstractId {
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

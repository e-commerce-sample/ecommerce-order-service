package com.ecommerce.order.order.exception;

import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.ProductId;
import com.ecommerce.shared.exception.AppException;

import static com.ecommerce.order.OrderErrorCode.PRODUCT_NOT_IN_ORDER;
import static com.google.common.collect.ImmutableMap.of;

public class ProductNotInOrderException extends AppException {
    public ProductNotInOrderException(ProductId productId, OrderId orderId) {
        super(PRODUCT_NOT_IN_ORDER, of("productId", productId.toString(),
                "orderId", orderId.toString()));
    }
}

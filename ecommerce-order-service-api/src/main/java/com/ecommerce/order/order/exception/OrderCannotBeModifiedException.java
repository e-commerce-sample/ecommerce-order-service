package com.ecommerce.order.order.exception;

import com.ecommerce.shared.exception.AppException;

import static com.ecommerce.order.OrderErrorCode.ORDER_CANNOT_BE_MODIFIED;
import static com.google.common.collect.ImmutableMap.of;

public class OrderCannotBeModifiedException extends AppException {
    public OrderCannotBeModifiedException(String id) {
        super(ORDER_CANNOT_BE_MODIFIED, of("orderId", id));
    }
}

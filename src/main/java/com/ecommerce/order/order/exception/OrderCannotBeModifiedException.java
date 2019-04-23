package com.ecommerce.order.order.exception;

import com.ecommerce.order.common.exception.AppException;
import com.ecommerce.order.order.model.OrderId;

import static com.ecommerce.order.common.exception.ErrorCode.ORDER_CANNOT_BE_MODIFIED;
import static com.google.common.collect.ImmutableMap.of;

public class OrderCannotBeModifiedException extends AppException {
    public OrderCannotBeModifiedException(OrderId id) {
        super(ORDER_CANNOT_BE_MODIFIED, of("orderId", id.toString()));
    }
}

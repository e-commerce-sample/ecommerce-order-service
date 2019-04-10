package com.ecommerce.order.common.exception;


import static com.ecommerce.order.common.exception.ErrorCode.SYSTEM_ERROR;
import static com.google.common.collect.ImmutableMap.of;

public class SystemException extends AppException {
    public SystemException(String detailMessage, Throwable cause) {
        super(SYSTEM_ERROR, of("detail", detailMessage), cause);
    }

    public SystemException(Throwable cause) {
        super(SYSTEM_ERROR, of("detail", cause.getMessage()), cause);
    }
}

package com.ecommerce.order.common.exception;

import java.util.Map;

import static com.ecommerce.order.common.exception.ErrorCode.REQUEST_VALIDATION_FAILED;

public class RequestValidationException extends AppException {
    public RequestValidationException(Map<String, Object> data) {
        super(REQUEST_VALIDATION_FAILED, data);
    }
}

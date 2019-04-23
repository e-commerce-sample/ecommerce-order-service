package com.ecommerce.order.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


public enum ErrorCode {
    PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE(CONFLICT, "支付价格与订单实际价格不符"),
    PRODUCT_NOT_IN_ORDER(CONFLICT, "订单不包含产品"),
    ORDER_CANNOT_BE_MODIFIED(CONFLICT, "订单无法变更"),
    PRODUCT_NOT_FOUND(NOT_FOUND, "没有找到产品"),
    LOCK_OCCUPIED(CONFLICT, "任务已被锁定，请稍后重试"),
    SYSTEM_ERROR(INTERNAL_SERVER_ERROR, "系统错误"),
    REQUEST_VALIDATION_FAILED(BAD_REQUEST, "请求数据格式验证失败"),
    ORDER_NOT_FOUND(NOT_FOUND, "没有找到订单");
    private HttpStatus status;
    private String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

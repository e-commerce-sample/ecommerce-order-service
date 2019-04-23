package com.ecommerce.order.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


public enum ErrorCode {
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

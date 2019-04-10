package com.ecommerce.order.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


public enum ErrorCode {
    SYSTEM_ERROR(INTERNAL_SERVER_ERROR, "系统错误"),
    REQUEST_VALIDATION_FAILED(BAD_REQUEST, "请求数据格式验证失败");
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

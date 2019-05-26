package com.ecommerce.common.exception;

public interface ErrorEnum {
    int getStatus();

    String getMessage();

    String getCode();
}

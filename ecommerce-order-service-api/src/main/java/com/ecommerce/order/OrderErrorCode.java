package com.ecommerce.order;


import com.ecommerce.shared.exception.ErrorCode;

public enum OrderErrorCode implements ErrorCode {
    ORDER_CANNOT_BE_MODIFIED(409, "订单无法变更"),
    ORDER_NOT_FOUND(404, "没有找到订单"),
    PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE(409, "支付价格与订单实际价格不符"),
    PRODUCT_NOT_IN_ORDER(409, "订单不包含产品");
    private int status;
    private String message;

    OrderErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}

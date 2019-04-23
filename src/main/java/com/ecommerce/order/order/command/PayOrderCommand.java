package com.ecommerce.order.order.command;

import com.ecommerce.order.common.ddd.Command;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PayOrderCommand implements Command {

    @NotNull(message = "支付金额不能为空")
    private BigDecimal paidPrice;

    @JsonCreator
    public PayOrderCommand(@JsonProperty("paidPrice") BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }
}

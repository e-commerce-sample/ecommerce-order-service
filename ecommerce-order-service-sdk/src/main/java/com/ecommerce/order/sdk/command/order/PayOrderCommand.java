package com.ecommerce.order.sdk.command.order;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class PayOrderCommand {

    @NotNull(message = "支付金额不能为空")
    private BigDecimal paidPrice;

}

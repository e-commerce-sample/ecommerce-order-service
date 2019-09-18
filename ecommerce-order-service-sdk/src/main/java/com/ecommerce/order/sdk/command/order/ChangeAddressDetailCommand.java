package com.ecommerce.order.sdk.command.order;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class ChangeAddressDetailCommand {
    @NotNull(message = "详细地址不能为空")
    private String detail;

}

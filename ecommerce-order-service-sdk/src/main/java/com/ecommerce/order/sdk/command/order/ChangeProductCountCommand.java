package com.ecommerce.order.sdk.command.order;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Value
public class ChangeProductCountCommand {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

}

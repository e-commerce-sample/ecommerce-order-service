package com.ecommerce.order.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChangeProductCountCommand {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

    @JsonCreator
    public ChangeProductCountCommand(@JsonProperty("productId") String productId,
                                     @JsonProperty("count") int count) {
        this.productId = productId;
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }
}

package com.ecommerce.order.order.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ChangeAddressDetailCommand {
    @NotNull(message = "详细地址不能为空")
    private String detail;

    @JsonCreator
    public ChangeAddressDetailCommand(@JsonProperty("detail") String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}

package com.ecommerce.order.product;

import com.ecommerce.order.common.ddd.Command;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateProductCommand implements Command {

    @NotBlank(message = "产品名字不能为空")
    private final String name;

    @NotBlank(message = "产品描述不能为空")
    private final String description;

    @NotNull(message = "产品价格不能为空")
    private final BigDecimal price;

    @JsonCreator
    public CreateProductCommand(@JsonProperty("name") String name,
                                @JsonProperty("description") String description,
                                @JsonProperty("price") BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

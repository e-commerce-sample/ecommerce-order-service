package com.ecommerce.order.common.utils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//Http request of list should be wrapped in this class in order to be validated
public class ListCommand<T> extends ArrayList<T> {
    @Valid
    public List<T> getList() {
        return this;
    }
}

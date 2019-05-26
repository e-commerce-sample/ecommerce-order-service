package com.ecommerce.order.common.model;

import com.ecommerce.order.common.ddd.Identity;
import com.ecommerce.order.common.ddd.ValueObject;

import java.util.Objects;

public abstract class AbstractId implements Identity, ValueObject {
    private String id;

    protected AbstractId() {
    }

    protected AbstractId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractId that = (AbstractId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }

}

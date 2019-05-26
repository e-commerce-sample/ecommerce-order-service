package com.ecommerce.order.common.model;

import com.ecommerce.order.common.ddd.ValueObject;

import java.util.Objects;

public class Address implements ValueObject {
    private String province;
    private String city;
    private String detail;

    private Address() {
    }

    private Address(String province, String city, String detail) {
        this.province = province;
        this.city = city;
        this.detail = detail;
    }

    public static Address of(String province, String city, String detail) {
        return new Address(province, city, detail);
    }

    public Address changeDetailTo(String detail) {
        return new Address(this.province, this.city, detail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return province.equals(address.province) &&
                city.equals(address.city) &&
                detail.equals(address.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, detail);
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDetail() {
        return detail;
    }
}

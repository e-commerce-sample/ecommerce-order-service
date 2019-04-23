package com.ecommerce.order.common.utils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PagedResource<T> {
    private int totalNumber;
    private int pageIndex;
    private List<T> resource;

    private PagedResource() {
    }

    private PagedResource(int totalNumber, int pageIndex, List<T> resource) {
        this.totalNumber = totalNumber;
        this.pageIndex = pageIndex;
        this.resource = resource;
    }

    public static <T> PagedResource<T> of(int totalNumber, int pageIndex, List<T> resource) {
        return new PagedResource<>(totalNumber, pageIndex, resource);
    }

    public static <T> PagedResource<T> emptyResult() {
        return new PagedResource<>(0, 0, Collections.emptyList());
    }

    public <K> PagedResource<K> map(Function<T, K> fun) {
        List<K> collect = resource.stream().parallel().map(fun).collect(Collectors.toList());
        return new PagedResource<>(totalNumber, pageIndex, collect);
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public List<T> getResource() {
        return resource;
    }
}

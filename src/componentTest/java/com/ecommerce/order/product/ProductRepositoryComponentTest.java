package com.ecommerce.order.product;

import com.ecommerce.order.BaseComponentTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductRepositoryComponentTest extends BaseComponentTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void should_save_order() {
        Product product = Product.create("娃哈哈", "小孩喝的娃哈哈", valueOf(2));
        repository.save(product);
        Product saved = repository.byId(product.getId());
        assertEquals(product.getId(), saved.getId());
    }

}
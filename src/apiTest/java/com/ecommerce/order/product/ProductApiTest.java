package com.ecommerce.order.product;

import com.ecommerce.order.BaseApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ecommerce.order.product.Product.create;
import static com.ecommerce.order.product.ProductId.productId;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.IntStream.range;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductApiTest extends BaseApiTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void should_create_product() {
        String id = given()
                .contentType("application/json")
                .body(new CreateProductCommand("喜乐多", "喜乐多真好喝", valueOf(2)))
                .when()
                .post("/products")
                .then().statusCode(201)
                .extract().body().jsonPath().getString("id");
        Product product = repository.byId(productId(id));
        assertNotNull(product);
        assertEquals(id, product.getId().toString());
    }

    @Test
    public void should_list_product_summary() {
        range(1, 10).forEach(value -> repository.save(create("喜乐多", "喜乐多真好喝", valueOf(5))));
        given()
                .when()
                .get("/products?pageIndex=2&pageSize=5")
                .then().statusCode(200)
                .body("pageIndex", is(2))
                .body("resource[0].name", is("喜乐多"));
    }

    @Test
    public void should_get_product_detail() {
        Product product = create("喜茶", "喜茶", valueOf(10));
        repository.save(product);
        given()
                .when().get("/products/{id}", product.getId().toString())
                .then().statusCode(200)
                .body("name", is("喜茶"));
    }


}
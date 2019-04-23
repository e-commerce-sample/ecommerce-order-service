package com.ecommerce.order.product;

import com.ecommerce.order.common.utils.PagedResource;
import com.ecommerce.order.product.representation.ProductSummaryRepresentation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductId createProduct(@RequestBody @Valid CreateProductCommand command) {
        // TODO: 创建Product
        return null;
    }

    @GetMapping
    public PagedResource<ProductSummaryRepresentation> pagedProducts(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // TODO: 获取Product列表
        return null;
    }


    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Object byId(@PathVariable(name = "id") String id) {
        // TODO: 获取单个Product详情
        return null;
    }

}

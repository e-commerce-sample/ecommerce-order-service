package com.ecommerce.order.product;

import com.ecommerce.order.common.utils.PagedResource;
import com.ecommerce.order.product.representation.ProductRepresentationService;
import com.ecommerce.order.product.representation.ProductSummaryRepresentation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductApplicationService productApplicationService;
    private final ProductRepresentationService productRepresentationService;

    public ProductController(ProductApplicationService productApplicationService,
                             ProductRepresentationService productRepresentationService) {
        this.productApplicationService = productApplicationService;
        this.productRepresentationService = productRepresentationService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductId createProduct(@RequestBody @Valid CreateProductCommand command) {
        return productApplicationService.create(command);
    }


    @GetMapping
    public PagedResource<ProductSummaryRepresentation> pagedProducts(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return productRepresentationService.listProducts(pageIndex, pageSize);
    }


    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Object byId(@PathVariable(name = "id") String id) {
        return productRepresentationService.byId(id);
    }

}

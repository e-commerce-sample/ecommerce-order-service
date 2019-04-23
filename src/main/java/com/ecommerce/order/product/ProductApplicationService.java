package com.ecommerce.order.product;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductApplicationService {

    private final ProductRepository repository;

    public ProductApplicationService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProductId create(CreateProductCommand command) {
        Product product = Product.create(command.getName(), command.getDescription(), command.getPrice());
        repository.save(product);
        return product.getId();
    }
}

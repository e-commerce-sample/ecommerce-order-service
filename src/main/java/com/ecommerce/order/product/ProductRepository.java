package com.ecommerce.order.product;

import com.ecommerce.order.common.ddd.Repository;
import com.ecommerce.order.common.utils.DefaultObjectMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class ProductRepository implements Repository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate,
                             DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    public void save(Product product) {
        String sql = "INSERT INTO PRODUCT (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", product.getId().toString(), "json", objectMapper.writeValueAsString(product));
        jdbcTemplate.update(sql, paramMap);
    }

    public Product byId(ProductId id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM PRODUCT WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id.toString()), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException(id);
        }
    }


    private RowMapper<Product> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Product.class);
    }

}

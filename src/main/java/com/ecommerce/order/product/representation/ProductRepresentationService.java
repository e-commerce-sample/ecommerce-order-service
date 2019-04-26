package com.ecommerce.order.product.representation;

import com.ecommerce.order.common.utils.PagedResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Maps.newHashMap;

@Component
public class ProductRepresentationService {
    public static final String SELECT_SQL = "SELECT ID, NAME, PRICE FROM PRODUCT LIMIT :limit OFFSET :offset;";
    public static final String COUNT_SQL = "SELECT COUNT(1) FROM PRODUCT;";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductRepresentationService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public PagedResource<ProductSummaryRepresentation> listProducts(int pageIndex, int pageSize) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("limit", pageSize);
        parameters.addValue("offset", (pageIndex - 1) * pageSize);

        List<ProductSummaryRepresentation> products = jdbcTemplate.query(SELECT_SQL, parameters,
                (rs, rowNum) -> new ProductSummaryRepresentation(rs.getString("ID"),
                        rs.getString("NAME"),
                        rs.getBigDecimal("PRICE")));

        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
        return PagedResource.of(total, pageIndex, products);
    }

    @Transactional(readOnly = true)
    public String byId(String id) {
        return jdbcTemplate.queryForObject("SELECT JSON_CONTENT FROM PRODUCT WHERE ID =:id;",
                new MapSqlParameterSource().addValue("id", id),
                String.class);
    }

}

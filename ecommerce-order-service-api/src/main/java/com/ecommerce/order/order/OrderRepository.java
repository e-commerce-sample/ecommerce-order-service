package com.ecommerce.order.order;

import com.ecommerce.order.order.exception.OrderNotFoundException;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.shared.jackson.DefaultObjectMapper;
import com.ecommerce.shared.model.BaseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class OrderRepository extends BaseRepository<Order> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate,
                           DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doSave(Order order) {
        String sql = "INSERT INTO ORDERS (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", order.getId(), "json", objectMapper.writeValueAsString(order));
        jdbcTemplate.update(sql, paramMap);
    }

    public Order byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM ORDERS WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(id);
        }
    }

    private RowMapper<Order> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Order.class);
    }

}

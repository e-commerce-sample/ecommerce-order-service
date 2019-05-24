package com.ecommerce.order.common.event;

import com.ecommerce.order.common.utils.DefaultObjectMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class DomainEventRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public DomainEventRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    public void save(DomainEvent event) {
        String sql = "INSERT INTO EVENT (ID, JSON_CONTENT) VALUES (:id, :json);";
        Map<String, String> paramMap = of("id", event.get_id(), "json", objectMapper.writeValueAsString(event));
        jdbcTemplate.update(sql, paramMap);
    }
}

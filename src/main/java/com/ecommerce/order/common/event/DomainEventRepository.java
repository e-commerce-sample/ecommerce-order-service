package com.ecommerce.order.common.event;

import com.ecommerce.order.common.utils.DefaultObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class DomainEventRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;
    private final RowMapper<DomainEvent> mapper;

    public DomainEventRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.mapper = mapper(objectMapper);
    }

    public void add(DomainEvent event) {
        String sql = "INSERT INTO EVENT (ID, JSON_CONTENT) VALUES (:id, :json);";
        Map<String, String> paramMap = of("id", event.get_id(), "json", objectMapper.writeValueAsString(event));
        jdbcTemplate.update(sql, paramMap);
    }

    public List<DomainEvent> newestEvents() {
        String sql = "SELECT JSON_CONTENT FROM EVENT ORDER BY CREATED_AT DESC LIMIT 10;";
        return jdbcTemplate.query(sql, mapper);
    }

    public void delete(String id) {
        String sql = "DELETE FROM EVENT WHERE ID = :id;";
        jdbcTemplate.update(sql, of("id", id));
    }

    private RowMapper<DomainEvent> mapper(DefaultObjectMapper objectMapper) {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), DomainEvent.class);
    }
}

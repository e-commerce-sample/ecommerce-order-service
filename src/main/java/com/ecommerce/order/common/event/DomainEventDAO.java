package com.ecommerce.order.common.event;

import com.ecommerce.order.common.utils.DefaultObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;

@Component
public class DomainEventDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;
    private final RowMapper<DomainEvent> mapper;

    public DomainEventDAO(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.mapper = mapper(objectMapper);
    }

    public void insert(List<DomainEvent> events) {
        String sql = "INSERT INTO EVENT (ID, JSON_CONTENT) VALUES (:id, :json);";

        SqlParameterSource[] parameters = events.stream()
                .map((Function<DomainEvent, SqlParameterSource>) domainEvent ->
                        new MapSqlParameterSource()
                                .addValue("id", domainEvent.get_id())
                                .addValue("json", objectMapper.writeValueAsString(domainEvent)))
                .toArray(SqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, parameters);
    }

    public void insert(DomainEvent event) {
        insert(newArrayList(event));
    }

    public List<DomainEvent> nextBatchEvents() {
        String sql = "SELECT JSON_CONTENT FROM EVENT WHERE PUBLISH_TRIES < 5 ORDER BY CREATED_AT LIMIT 50;";
        return jdbcTemplate.query(sql, mapper);
    }

    public void delete(String id) {
        String sql = "DELETE FROM EVENT WHERE ID = :id;";
        jdbcTemplate.update(sql, of("id", id));
    }

    private RowMapper<DomainEvent> mapper(DefaultObjectMapper objectMapper) {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), DomainEvent.class);
    }

    public void increasePublishTries(String id) {
        String sql = "UPDATE EVENT SET PUBLISH_TRIES = PUBLISH_TRIES + 1 WHERE ID = :id;";
        jdbcTemplate.update(sql, ImmutableMap.of("id", id));
    }
}

package com.ecommerce.order.order.representation;

import com.ecommerce.order.order.OrderRepository;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.sdk.representation.order.OrderRepresentation;
import com.ecommerce.order.sdk.representation.order.OrderSummaryRepresentation;
import com.ecommerce.shared.jackson.DefaultObjectMapper;
import com.ecommerce.shared.utils.PagedResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

@Slf4j
@Component
public class OrderRepresentationService {

    private static final String SELECT_SQL = "SELECT JSON_CONTENT FROM ORDER_SUMMARY LIMIT :limit OFFSET :offset;";
    private static final String COUNT_SQL = "SELECT COUNT(1) FROM ORDER_SUMMARY;";
    private final OrderRepository orderRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public OrderRepresentationService(OrderRepository orderRepository,
                                      NamedParameterJdbcTemplate jdbcTemplate,
                                      DefaultObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        Order order = orderRepository.byId(id);
        return order.toRepresentation();
    }

    @Transactional(readOnly = true)
    public PagedResource<OrderSummaryRepresentation> listOrders(int pageIndex, int pageSize) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("limit", pageSize);
        parameters.addValue("offset", (pageIndex - 1) * pageSize);

        List<OrderSummaryRepresentation> products = jdbcTemplate.query(SELECT_SQL, parameters,
                (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"),
                        OrderSummaryRepresentation.class)
        );

        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
        return PagedResource.of(total, pageIndex, products);
    }

    @Transactional
    public void cqrsSync(String id) {
        Order order = orderRepository.byId(id);
        OrderSummaryRepresentation summary = order.toSummary();
        String sql = "INSERT INTO ORDER_SUMMARY (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", summary.getId(), "json", objectMapper.writeValueAsString(summary));
        jdbcTemplate.update(sql, paramMap);
        log.info("Order[{}] summary synced due to CQRS.", id);
    }

}

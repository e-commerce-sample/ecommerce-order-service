package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class DomainEventReceiveRecorder {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private NamedParameterJdbcTemplate jdbcTemplate;

    public DomainEventReceiveRecorder(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Optional<Object> optionalEvent = Arrays.stream(args)
                .filter(o -> o instanceof DomainEvent)
                .findFirst();

        if (optionalEvent.isPresent()) {
            String endpointTag = constructEndPointTag(joinPoint);
            DomainEvent event = (DomainEvent) optionalEvent.get();
            try {
                recordEvent(endpointTag, event);
            } catch (DuplicateKeyException dke) {
                logger.warn("Duplicated {} skipped for {}.", event, endpointTag);
                return null;
            }

            return joinPoint.proceed();
        }

        return joinPoint.proceed();
    }

    private String constructEndPointTag(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        return className + ":" + methodName;
    }

    private void recordEvent(String endpointTag, DomainEvent event) {
        String sql = "INSERT INTO EVENT_RECEIVE_RECORD (ENDPOINT_TAG, EVENT_ID) VALUES (:tag, :eventId);";
        jdbcTemplate.update(sql, of("tag", endpointTag, "eventId", event.get_id()));
    }
}

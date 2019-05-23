package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.utils.UuidGenerator;

import java.time.Instant;

public abstract class DomainEvent {
    private String id;
    private EventType type;
    private Instant createdAt;

    private DomainEvent() {
    }

    protected DomainEvent(EventType type) {
        this.id = UuidGenerator.newUuid();
        this.type = type;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public EventType getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}

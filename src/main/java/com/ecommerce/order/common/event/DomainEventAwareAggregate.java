package com.ecommerce.order.common.event;

import com.ecommerce.order.common.ddd.AggregateRoot;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class DomainEventAwareAggregate implements AggregateRoot {
    private final List<DomainEvent> events = newArrayList();

    protected void raiseEvent(DomainEvent event) {
        this.events.add(event);
    }

    void clearEvents() {
        this.events.clear();
    }

    List<DomainEvent> getEvents() {
        return events;
    }
}

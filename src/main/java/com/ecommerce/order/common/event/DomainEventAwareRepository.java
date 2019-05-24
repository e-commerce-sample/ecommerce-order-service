package com.ecommerce.order.common.event;

import com.ecommerce.order.common.ddd.Repository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DomainEventAwareRepository<AR extends DomainEventAwareAggregate> implements Repository {
    @Autowired
    private DomainEventRepository eventRepository;

    public void save(AR aggregate) {
        aggregate.getEvents().forEach(eventRepository::save);
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}

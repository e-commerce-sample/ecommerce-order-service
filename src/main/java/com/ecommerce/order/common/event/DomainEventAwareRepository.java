package com.ecommerce.order.common.event;

import com.ecommerce.order.common.ddd.Repository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DomainEventAwareRepository<AR extends DomainEventAwareAggregate> implements Repository {
    @Autowired
    private DomainEventDAO eventDAO;

    public void save(AR aggregate) {
        eventDAO.insert(aggregate.getEvents());
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}

package org.eduorg.msauth.common.domain.aggregate_root;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.common.domain.entity.Entity;
import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserStateException;

import java.util.List;

public abstract class AggregateRoot<T extends ValueObject<T>> extends Entity<T> {

    protected List<DomainEvents> domainEvents;

    public AggregateRoot(T id, DomainEvents event) {
        super(id);
        this.onEvent(event);
    }

    public void onEvent(DomainEvents event) {
        this.domainEvents.add(event);
    }

    public abstract void applyEvent(DomainEvents event);

    public abstract void ensureValidState() throws InvalidUserStateException;

    public List<DomainEvents> pullDomainEvents() {
        List<DomainEvents> events = this.domainEvents;
        this.domainEvents.clear();
        return events;
    }
}

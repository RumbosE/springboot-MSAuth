package org.eduorg.msauth.common.domain.aggregate_root;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.common.domain.entity.Entity;
import org.eduorg.msauth.common.domain.value_object.ValueObject;

import java.util.ArrayList;

public abstract class AggregateRoot<T extends ValueObject<T>> extends Entity<T> {

    protected ArrayList<DomainEvents> domainEvents = new ArrayList<>();

    public AggregateRoot(T id, DomainEvents event) {
        super(id);
        this.onEvent(event);
    }

    public void onEvent(DomainEvents event) {
        this.domainEvents.add(event);
    }

    public abstract void applyEvent(DomainEvents event);

    public abstract void ensureValidState();

    public ArrayList<DomainEvents> pullDomainEvents() {
        ArrayList<DomainEvents> events = this.domainEvents;
        this.domainEvents.clear();
        return events;
    }
}

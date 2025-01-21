package org.eduorg.msauth.common.domain.domain_events;

import java.util.Date;

public abstract class DomainEvents {
    public Date occurredOn;

    public DomainEvents() {
        this.occurredOn = new Date();
    }

    public Date getOccurredOn() {
        return occurredOn;
    }

    public String eventName() {
        return this.getClass().getName();
    }

}

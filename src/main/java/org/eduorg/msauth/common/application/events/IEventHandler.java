package org.eduorg.msauth.common.application.events;
import org.eduorg.msauth.common.domain.domain_events.DomainEvents;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface IEventHandler {
     Future<Void> publish(List<DomainEvents> event);

     Future<IEventSubscriber> subscribe(String eventName, Consumer<DomainEvents> callback);
}

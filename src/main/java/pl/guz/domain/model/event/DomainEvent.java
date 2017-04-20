package pl.guz.domain.model.event;

import org.joda.time.DateTime;

import java.util.UUID;

public interface DomainEvent {

    UUID id();

    DateTime occuredOn();

    int eventVersion();

    String type();
}

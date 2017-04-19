package pl.guz.domain.model.event;

import java.util.Date;
import java.util.UUID;

public interface DomainEvent {

    UUID id();

    Date occuredOn();

    int eventVersion();

    String type();
}

package pl.guz.domain.model.event;

import java.util.Date;

public interface DomainEvent {

    Date occuredOn();
    int eventVersion();
    String type();
}

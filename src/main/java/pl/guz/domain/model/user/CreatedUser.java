package pl.guz.domain.model.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.joda.time.DateTime;
import pl.guz.domain.model.event.DomainEvent;

import java.util.UUID;

@Value
@Getter(AccessLevel.NONE)
public class CreatedUser implements DomainEvent {
    private UUID id;
    private DateTime occuredOn;
    private String name;
    private int eventVersion;
    private String type = "pl.guz.domain.model.user.CreateUser";

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public DateTime occuredOn() {
        return occuredOn;
    }

    @Override
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    public String type() {
        return type;
    }

    public String name() {
        return name;
    }

    public CreatedUser(UUID id, DateTime occuredOn, String name) {
        this.id = id;
        this.occuredOn = occuredOn;
        this.name = name;
        this.eventVersion = 0;
    }

    public CreatedUser(UUID id,
                       DateTime occuredOn,
                       String name,
                       Integer eventVersion) {
        this.id = id;
        this.occuredOn = occuredOn;
        this.name = name;
        this.eventVersion = eventVersion;
    }
}

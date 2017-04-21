package pl.guz;

import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import pl.guz.domain.model.event.DomainEvent;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode
public class TestDomainEvent implements DomainEvent {
    private final UUID id;
    private final DateTime occuredOn;
    private final String name;
    private final BigDecimal value;

    public TestDomainEvent(UUID id, DateTime occuredOn, String name, BigDecimal value) {
        this.id = id;
        this.occuredOn = occuredOn;
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

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
        return 0;
    }

    @Override
    public String type() {
        return "TestDomainEvent";
    }
}
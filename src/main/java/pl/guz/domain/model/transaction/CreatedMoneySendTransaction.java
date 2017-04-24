package pl.guz.domain.model.transaction;

import lombok.Value;
import org.joda.time.DateTime;
import pl.guz.domain.model.event.DomainEvent;

import java.util.UUID;

@Value
public class CreatedMoneySendTransaction implements DomainEvent {

    private UUID id;
    private DateTime occuredOn;
    private int value;
    private String currency;

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
        return 1;
    }

    @Override
    public String type() {
        return "pl.guz.domain.model.transaction.CreatedMoneySendTransaction";
    }

    public int value() {
        return value;
    }

    public String currency() {
        return currency;
    }
}

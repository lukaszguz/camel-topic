package pl.guz.domain.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.guz.domain.model.event.DomainEvent;

import java.util.Date;
import java.util.UUID;

@Value
public class CreatedMoneySendTransaction implements DomainEvent {

    private UUID id;
    private Date occuredOn;
    private int value;
    private String currency;

    @Override
    @JsonProperty
    public UUID id() {
        return id;
    }

    @Override
    @JsonProperty
    public Date occuredOn() {
        return occuredOn;
    }

    @Override
    @JsonProperty
    public int eventVersion() {
        return 1;
    }

    @Override
    @JsonProperty
    public String type() {
        return "pl.guz.domain.model.transaction.CreatedMoneySendTransaction";
    }

    @JsonProperty
    public int value() {
        return value;
    }

    @JsonProperty
    public String currency() {
        return currency;
    }
}

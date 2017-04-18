package pl.guz.domain.model.transaction;


import lombok.Value;
import pl.guz.domain.model.event.DomainEvent;

import java.util.Date;

@Value
public class CreatedMoneySendTransaction implements DomainEvent {

    private Date occuredOn;
    private int value;
    private String currency;

    @Override
    public Date occuredOn() {
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

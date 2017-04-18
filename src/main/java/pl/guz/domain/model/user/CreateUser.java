package pl.guz.domain.model.user;

import lombok.Value;
import pl.guz.domain.model.event.DomainEvent;

import java.util.Date;

@Value
public class CreateUser implements DomainEvent {

    private Date occuredOn;
    private String name;

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
        return "pl.guz.domain.model.user.CreateUser";
    }

    public String name() {
        return name;
    }
}

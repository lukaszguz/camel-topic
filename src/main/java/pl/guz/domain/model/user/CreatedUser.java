package pl.guz.domain.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import pl.guz.domain.model.event.DomainEvent;

import java.util.Date;
import java.util.UUID;

@Value
//@Getter(AccessLevel.NONE)
public class CreatedUser implements DomainEvent {
    @JsonProperty
    private UUID id;
    @JsonProperty
    private Date occuredOn;
    @JsonProperty
    private String name;
    @JsonIgnore
    private int eventVersion;
    @JsonIgnore
    private String type = "pl.guz.domain.model.user.CreateUser";

    @Override
    @JsonProperty
    public UUID id() {
        return id;
    }

    @Override
    @JsonProperty("occured_on")
    public Date occuredOn() {
        return occuredOn;
    }

    @Override
    @JsonProperty("event_version")
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    @JsonProperty
    public String type() {
        return type;
    }

    @JsonProperty
    public String name() {
        return name;
    }

    public CreatedUser(UUID id, Date occuredOn, String name) {
        this.id = id;
        this.occuredOn = occuredOn;
        this.name = name;
        this.eventVersion = 0;
    }

    @JsonCreator
    public CreatedUser(@JsonProperty("id") UUID id,
                       @JsonProperty("occured_on") Date occuredOn,
                       @JsonProperty("name") String name,
                       @JsonProperty("event_version") Integer eventVersion
                       ) {
        this.id = id;
        this.occuredOn = occuredOn;
        this.name = name;
        this.eventVersion = eventVersion;
    }
}

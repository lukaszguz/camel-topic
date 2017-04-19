package pl.guz.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("events")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DomainEventEntity {

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    @PrimaryKeyColumn(name = "occured_on", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Date occuredOn;

    @PrimaryKeyColumn(name = "event_version", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private Integer eventVersion;

    @PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.PARTITIONED)
    private String type;

    @Column("event")
    @NotBlank
    private String event;

    public DomainEventEntity(DomainEvent domainEvent, String serializedEvent) {
        this.id = domainEvent.id();
        this.occuredOn = domainEvent.occuredOn();
        this.eventVersion = domainEvent.eventVersion();
        this.type = domainEvent.type();
        this.event = serializedEvent;
    }
}

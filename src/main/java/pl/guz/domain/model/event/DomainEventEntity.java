package pl.guz.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

import static com.datastax.driver.core.DataType.Name.TIMESTAMP;
import static com.datastax.driver.core.DataType.Name.UUID;

@Table("events")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DomainEventEntity {

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = UUID)
    private UUID id;

    @PrimaryKeyColumn(name = "occured_on", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = TIMESTAMP)
    private DateTime occuredOn;

    @PrimaryKeyColumn(name = "event_version", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private Integer eventVersion;

    @PrimaryKeyColumn(name = "type", ordinal = 3, type = PrimaryKeyType.PARTITIONED)
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

    public String serializedDomainEvent() {
        return event;
    }
}

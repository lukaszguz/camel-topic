package pl.guz.domain.model.event;

import lombok.AllArgsConstructor;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EventRepository {

    private final CassandraOperations cassandraOperations;

    public DomainEventEntity save(DomainEventEntity domainEventEntity) {
        return cassandraOperations.insert(domainEventEntity);
    }
}

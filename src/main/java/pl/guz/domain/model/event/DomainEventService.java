package pl.guz.domain.model.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.guz.serializer.DomainEventToStringConverter;

@Service
@Slf4j
public class DomainEventService {

    private DomainEventToStringConverter domainEventToStringConverter = new DomainEventToStringConverter();

    public DomainEventEntity convertToEntity(DomainEvent domainEvent) {
        log.info("Start converting DomainEvent to entity: {}, type: {}", domainEvent.id(), domainEvent.type());
        String domainEventAsJson = domainEventToStringConverter.convert(domainEvent);
        return new DomainEventEntity(domainEvent, domainEventAsJson);
    }
}

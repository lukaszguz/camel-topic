package pl.guz.domain.model.event.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import pl.guz.domain.model.event.DomainEvent;

public class DomainEventToStringConverter implements Converter<DomainEvent, String> {

    private final ObjectMapper mapper;

    public DomainEventToStringConverter() {
        mapper = new ObjectMapper();
    }

    public DomainEventToStringConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @SneakyThrows
    public String convert(DomainEvent domainEvent) {
        return mapper.writeValueAsString(domainEvent);
    }
}

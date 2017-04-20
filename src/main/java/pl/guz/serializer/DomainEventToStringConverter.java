package pl.guz.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import pl.guz.domain.model.event.DomainEvent;

public class DomainEventToStringConverter implements Converter<DomainEvent, String> {

    private Gson gson = new GsonBuilder().serializeNulls()
                                         .registerTypeAdapter(DateTime.class, new JodaDateTimeGsonSerializer())
                                         .create();

    @Override
    public String convert(DomainEvent domainEvent) {
        JsonObject json = gson.toJsonTree(domainEvent)
                              .getAsJsonObject();
        json.addProperty("eventVersion", domainEvent.eventVersion());
        json.addProperty("type", domainEvent.type());
        return json.toString();
    }
}

package pl.guz.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

public class JodaDateTimeGsonDeserializer implements JsonDeserializer<DateTime> {
    @Override
    public DateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ISODateTimeFormat.dateTime()
                                .parseDateTime(jsonElement.getAsString());
    }
}

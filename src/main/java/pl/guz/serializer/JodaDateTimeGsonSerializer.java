package pl.guz.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

public class JodaDateTimeGsonSerializer implements JsonSerializer<DateTime> {
    @Override
    public JsonElement serialize(DateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(ISODateTimeFormat.dateTime()
                                                  .print(dateTime));
    }
}

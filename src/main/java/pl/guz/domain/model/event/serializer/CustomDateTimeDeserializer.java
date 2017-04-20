package pl.guz.domain.model.event.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class CustomDateTimeDeserializer extends JsonDeserializer<DateTime> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);

    @Override
    public DateTime deserialize(JsonParser parser, DeserializationContext ctxt)
    throws IOException, JsonProcessingException {
        DateTime dateTime = new DateTime();
        ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        return dateTime.withDate(node.get("yearOfEra")
                                     .asInt(),
                                 node.get("monthOfYear")
                                     .asInt(),
                                 node.get("dayOfMonth")
                                     .asInt());

    }
}
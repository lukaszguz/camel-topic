package pl.guz.deserializer

import com.google.gson.JsonPrimitive
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import pl.guz.deserializer.JodaDateTimeGsonDeserializer
import spock.lang.Specification

class JodaDateTimeGsonDeserializerSpec extends Specification {

    private JodaDateTimeGsonDeserializer deserializer = new JodaDateTimeGsonDeserializer()

    def "Should deserialize joda data time from json"() {
        given:
        String dateTimeAsString = '2017-12-31T11:12:00.000-02:00'
        DateTime expectedDateTime = new DateTime(2017, 12, 31, 11, 12, 0, 0, DateTimeZone.forOffsetHours(-2))

        expect:
        deserializer.deserialize(new JsonPrimitive(dateTimeAsString), null, null) == expectedDateTime
    }
}

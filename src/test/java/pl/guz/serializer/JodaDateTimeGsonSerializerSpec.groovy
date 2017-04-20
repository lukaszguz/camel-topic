package pl.guz.serializer

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import spock.lang.Specification

class JodaDateTimeGsonSerializerSpec extends Specification {

    private JodaDateTimeGsonSerializer serializer = new JodaDateTimeGsonSerializer()

    def "Should serialize joda data time to json"() {
        given:
        DateTime dateTime = new DateTime(2017, 12, 31, 11, 12, 0, 0, DateTimeZone.forOffsetHours(-2))

        expect:
        serializer.serialize(dateTime, null, null).getAsString() == '2017-12-31T11:12:00.000-02:00'
    }
}

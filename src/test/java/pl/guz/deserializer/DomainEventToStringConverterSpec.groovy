package pl.guz.deserializer

import org.joda.time.DateTime
import org.joda.time.DateTimeUtils
import pl.guz.TestDomainEvent
import pl.guz.serializer.DomainEventToStringConverter
import spock.lang.Specification

class DomainEventToStringConverterSpec extends Specification {

    private final DateTime time = new DateTime(2017, 4, 2, 12, 22)
    private DomainEventToStringConverter converter = new DomainEventToStringConverter()

    def setup() {
        DateTimeUtils.setCurrentMillisFixed(time.millis)
    }

    def "Should convert domain event to json"() {
        given:
        UUID uuid = UUID.fromString('cd58fae7-b1b3-4fcf-81dd-33e57bb2fca1')
        TestDomainEvent domainEvent = new TestDomainEvent(uuid, time, "Jan Kowalski", new BigDecimal('123.45'))

        expect:
        converter.convert(domainEvent) == '{"id":"cd58fae7-b1b3-4fcf-81dd-33e57bb2fca1","occuredOn":"2017-04-02T12:22:00.000+02:00","name":"Jan Kowalski","value":123.45,"eventVersion":0,"type":"TestDomainEvent"}'
    }
}

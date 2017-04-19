package pl.guz.domain.model.event.converter

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime
import org.joda.time.DateTimeUtils
import pl.guz.domain.model.event.DomainEvent
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
        TestDomainEvent domainEvent = new TestDomainEvent(uuid, time.toDate(), "Jan Kowalski", new BigDecimal('123.45'))

        expect:
        converter.convert(domainEvent) == """{"id":"cd58fae7-b1b3-4fcf-81dd-33e57bb2fca1","occuredOn":1491128520000,"name":"Jan Kowalski","value":123.45,"eventVersion":0,"type":"TestDomainEvent"}"""
    }


    class TestDomainEvent implements DomainEvent {

        private final UUID id
        private final Date occuredOn
        private final String name
        private final BigDecimal value

        TestDomainEvent(UUID id, Date occuredOn, String name, BigDecimal value) {
            this.id = id
            this.occuredOn = occuredOn
            this.name = name
            this.value = value
        }

        @Override
        @JsonProperty
        UUID id() {
            return id
        }

        @Override
        @JsonProperty
        Date occuredOn() {
            return occuredOn
        }

        @Override
        @JsonProperty
        int eventVersion() {
            return 0
        }

        @Override
        @JsonProperty
        String type() {
            return 'TestDomainEvent'
        }

        @JsonProperty
        String name() {
            name
        }

        @JsonProperty
        BigDecimal value() {
            value
        }

    }
}
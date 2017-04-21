package pl.guz.domain.model.event

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import pl.guz.TestDomainEvent
import spock.lang.Specification

class DomainEventServiceSpec extends Specification {

    DomainEventService domainEventService = new DomainEventService()

    def "Should convert domain event to entity"() {
        given:
        UUID eventId = UUID.fromString('cd58fae7-b1b3-4fcf-81dd-33e57bb2fca1')
        DateTime occuredOn = new DateTime(2017, 12, 31, 11, 12, 0, 0, DateTimeZone.forOffsetHours(-2))
        TestDomainEvent domainEvent = new TestDomainEvent(eventId,occuredOn, "Test", new BigDecimal("123.12"))

        expect:
        domainEventService.convertToEntity(domainEvent) == new DomainEventEntity(domainEvent, """{"id":"$eventId","occuredOn":"2017-12-31T11:12:00.000-02:00","name":"Test","value":123.12,"eventVersion":0,"type":"TestDomainEvent"}""")
    }
}

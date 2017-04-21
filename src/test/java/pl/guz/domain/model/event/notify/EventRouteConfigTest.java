package pl.guz.domain.model.event.notify;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.guz.TestDomainEvent;
import pl.guz.domain.model.event.DomainEventService;
import pl.guz.domain.model.event.EventRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static pl.guz.domain.infrastructure.Exchanges.EVENT_NOTIFIER;
import static pl.guz.domain.infrastructure.Exchanges.EVENT_STORE;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventRouteConfigTest extends CamelTestSupport {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    DomainEventService domainEventService;

    @Test
    public void testRoute() throws Exception {
        // given:
        RouteDefinition route = context.getRouteDefinition("event_notifier_route");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveByToString(".*" + EVENT_NOTIFIER + ".*")
                        .after()
                        .to("mock:event-notifier");
            }
        });

        MockEndpoint mockEndpoint = getMockEndpoint("mock:event-notifier");
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.message(0)
                    .body(String.class)
                    .isEqualTo("{\"id\":\"5c6b4a66-bdd8-47a7-9b4a-945339a8f9fd\"," +
                               "\"occuredOn\":\"2017-04-21T13:40:56.872+02:00\"," +
                               "\"name\":\"Test\"," +
                               "\"value\":123.45," +
                               "\"eventVersion\":0," +
                               "\"type\":\"TestDomainEvent\"}");
        // when:
        context.start();
        sendBody(EVENT_STORE, new TestDomainEvent(
                UUID.fromString("5c6b4a66-bdd8-47a7-9b4a-945339a8f9fd"),
                new DateTime(2017, 4, 21, 13, 40, 56, 872, DateTimeZone.forOffsetHours(2)),
                "Test",
                new BigDecimal("123.45")));

        // then:
        mockEndpoint.assertIsSatisfied();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new EventRouteConfig().eventRoute(eventRepository, domainEventService);
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }
}
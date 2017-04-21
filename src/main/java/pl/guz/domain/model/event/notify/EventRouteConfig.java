package pl.guz.domain.model.event.notify;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.guz.domain.model.event.DomainEvent;
import pl.guz.domain.model.event.DomainEventEntity;
import pl.guz.domain.model.event.DomainEventService;
import pl.guz.domain.model.event.EventRepository;
import pl.guz.serializer.DomainEventToStringConverter;

import static org.apache.camel.component.rabbitmq.RabbitMQConstants.ROUTING_KEY;
import static pl.guz.domain.infrastructure.Exchanges.*;
import static pl.guz.domain.infrastructure.RoutingIds.EVENT_NOTIFIER_ROUTE;

@Component
@Slf4j
public class EventRouteConfig {

    private DomainEventToStringConverter domainEventToStringConverter = new DomainEventToStringConverter();

    @Bean
    RoutesBuilder eventRoute(EventRepository eventRepository, DomainEventService domainEventService) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(EVENT_STORE)
                        .routeId(EVENT_NOTIFIER_ROUTE)
                        .setHeader(ROUTING_KEY, bodyAs(DomainEvent.class).method("type"))
                        .bean(domainEventService)
                        .bean(eventRepository)
                        .transform(bodyAs(DomainEventEntity.class).method("serializedDomainEvent"))
                        .log("Body after convert to json: ${body}")
                        .to(EVENT_NOTIFIER);

//                        .to("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic" +
//                            "&autoDelete=false" +
//                            "&skipQueueDeclare=true");

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic" +
                     "&routingKey=pl.guz.domain.model.transaction.CreatedMoneySendTransaction" +
                     "&queue=created.transaction" +
                     "&autoDelete=false")
                        .to(CREATED_MONEY_SEND_TRANSACTION);

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic" +
                     "&routingKey=pl.guz.domain.model.user.CreateUser" +
                     "&autoDelete=false" +
                     "&queue=created.user")
                        .to(CREATED_USER);
            }
        };
    }
}

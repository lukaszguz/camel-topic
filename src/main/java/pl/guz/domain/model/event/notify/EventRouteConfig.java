package pl.guz.domain.model.event.notify;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import pl.guz.domain.infrastructure.Exchanges;
import pl.guz.domain.model.event.DomainEvent;
import pl.guz.domain.model.event.DomainEventEntity;
import pl.guz.domain.model.event.converter.DomainEventToStringConverter;

@Component
@Slf4j
public class EventRouteConfig {

    @Autowired
    private CassandraOperations cassandraOperations;
    private DomainEventToStringConverter domainEventToStringConverter = new DomainEventToStringConverter();

    @Bean
    RoutesBuilder eventRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(Exchanges.EVENT_NOTIFIER)
                        .routeId("event_notifier_route")
                        .setHeader("rabbitmq.ROUTING_KEY", bodyAs(DomainEvent.class).method("type"))
                        .process(exchange -> {
                            DomainEvent domainEvent = exchange.getIn()
                                                              .getBody(DomainEvent.class);
                            String domainEventAsJson = domainEventToStringConverter.convert(domainEvent);
                            DomainEventEntity domainEventEntity = new DomainEventEntity(domainEvent, domainEventAsJson);
                            exchange.getIn()
                                    .setBody(domainEventEntity);

                            log.info("Wysyłamy gościa: {}", domainEventEntity);
                        })
                        .process(exchange -> cassandraOperations.insert(exchange.getIn().getBody()))
                        .marshal()
                        .json(JsonLibrary.Jackson)
                        .log("Elo  ${body}")
                        .to("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic");

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&routingKey=pl.guz.domain.model.transaction.CreatedMoneySendTransaction")
                        .to(Exchanges.CREATED_MONEY_SEND_TRANSACTION);

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&routingKey=pl.guz.domain.model.user.CreateUser")
                        .to(Exchanges.CREATED_USER);
            }
        };
    }
}

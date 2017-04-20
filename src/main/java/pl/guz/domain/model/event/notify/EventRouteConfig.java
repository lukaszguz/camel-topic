package pl.guz.domain.model.event.notify;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import pl.guz.domain.infrastructure.Exchanges;
import pl.guz.domain.model.event.DomainEvent;
import pl.guz.domain.model.event.DomainEventEntity;
import pl.guz.serializer.DomainEventToStringConverter;

import static org.apache.camel.component.rabbitmq.RabbitMQConstants.ROUTING_KEY;

@Component
@Slf4j
public class EventRouteConfig {

    private DomainEventToStringConverter domainEventToStringConverter = new DomainEventToStringConverter();

    @Bean
    RoutesBuilder eventRoute(CassandraOperations cassandraOperations) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(Exchanges.EVENT_NOTIFIER)
                        .routeId("event_notifier_route")
                        .setHeader(ROUTING_KEY, bodyAs(DomainEvent.class).method("type"))
                        .process(exchange -> {
                            DomainEvent domainEvent = exchange.getIn()
                                                              .getBody(DomainEvent.class);
                            String domainEventAsJson = domainEventToStringConverter.convert(domainEvent);
                            DomainEventEntity domainEventEntity = new DomainEventEntity(domainEvent, domainEventAsJson);
                            exchange.getIn()
                                    .setBody(domainEventEntity);
                        })

                        .process(exchange -> cassandraOperations.insert(exchange.getIn().getBody()))
                        .marshal()
                        .json(JsonLibrary.Jackson)
                        .log("Body after convert to json: ${body}")
                        .to("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&autoDelete=false&skipQueueDeclare=true");

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&routingKey=pl.guz.domain.model.transaction.CreatedMoneySendTransaction&queue=created.transaction")
                        .to(Exchanges.CREATED_MONEY_SEND_TRANSACTION);

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&routingKey=pl.guz.domain.model.user.CreateUser&autoDelete=false&queue=created.user")
                        .to(Exchanges.CREATED_USER);
            }
        };
    }
}

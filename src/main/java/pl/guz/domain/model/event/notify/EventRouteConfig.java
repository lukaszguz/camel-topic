package pl.guz.domain.model.event.notify;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.guz.domain.infrastructure.Exchanges;
import pl.guz.domain.model.event.DomainEvent;

@Component
public class EventRouteConfig {


    @Bean
    RoutesBuilder eventRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(Exchanges.EVENT_NOTIFIER)
                        .setHeader("rabbitmq.ROUTING_KEY", bodyAs(DomainEvent.class).method("type"))
                        .transform(body().method("toString"))
                        .to("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic");

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&routingKey=pl.guz.domain.model.transaction.CreatedMoneySendTransaction")
                        .to(Exchanges.CREATED_MONEY_SEND_TRANSACTION);

                from("rabbitmq://localhost:5672/guz.event?username=admin&password=admin&exchangeType=topic&routingKey=pl.guz.domain.model.user.CreateUser")
                        .to(Exchanges.CREATED_USER);
            }
        };
    }
}

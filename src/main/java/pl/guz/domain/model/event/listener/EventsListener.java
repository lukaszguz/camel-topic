package pl.guz.domain.model.event.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.rx.ReactiveCamel;
import org.springframework.stereotype.Component;
import pl.guz.domain.infrastructure.Exchanges;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
@Slf4j
public class EventsListener {

    private final ReactiveCamel reactiveCamel;

    @PostConstruct
    void init() {
        subscribe();
    }

    public void subscribe() {
        rx.Observable.merge(
                reactiveCamel.toObservable(Exchanges.CREATED_MONEY_SEND_TRANSACTION, String.class),
                reactiveCamel.toObservable(Exchanges.CREATED_USER, String.class))
                .doOnNext(event -> log.info("I catch event: {}", event))
                .subscribe();

    }

}

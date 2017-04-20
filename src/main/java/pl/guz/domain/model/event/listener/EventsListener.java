package pl.guz.domain.model.event.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.rx.ReactiveCamel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static pl.guz.domain.infrastructure.Exchanges.CREATED_MONEY_SEND_TRANSACTION;
import static pl.guz.domain.infrastructure.Exchanges.CREATED_USER;

@Component
@AllArgsConstructor
@Slf4j
public class EventsListener {

    private final ReactiveCamel reactiveCamel;

    @PostConstruct
    void init() {
        subscribe();
    }

    private void subscribe() {
        rx.Observable.merge(
                reactiveCamel.toObservable(CREATED_MONEY_SEND_TRANSACTION, String.class),
                reactiveCamel.toObservable(CREATED_USER, String.class))
                    .doOnNext(event -> log.info("I catch event: {}", event))
                    .subscribe();

    }

}

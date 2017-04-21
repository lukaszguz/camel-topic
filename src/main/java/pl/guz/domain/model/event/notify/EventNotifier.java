package pl.guz.domain.model.event.notify;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.rx.ReactiveCamel;
import org.springframework.stereotype.Component;
import pl.guz.domain.model.event.DomainEvent;
import rx.Observable;

import static pl.guz.domain.infrastructure.Exchanges.EVENT_STORE;

@Component
@AllArgsConstructor
@Slf4j
public class EventNotifier {

    private final ReactiveCamel reactiveCamel;

    public void publishEvent(DomainEvent domainEvent) {
        log.info("Send event {}", domainEvent);
        reactiveCamel.sendTo(
                Observable.just(domainEvent), EVENT_STORE);
    }
}

package pl.guz.domain.model.transaction;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.guz.domain.model.event.notify.EventNotifier;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
@AllArgsConstructor
public class TransactionService {

    private final EventNotifier eventNotifier;

    @PostConstruct
    void init() {
        createTransaction();
    }

    public void createTransaction() {
        // ... creating transaction
        eventNotifier.publishEvent(new CreatedMoneySendTransaction(new Date(), 100, "PLN"));
    }
}

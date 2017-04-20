package pl.guz.domain.model.transaction;

import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import pl.guz.domain.model.event.notify.EventNotifier;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final EventNotifier eventNotifier;

    public void createTransaction() {
        // ... creating transaction
        eventNotifier.publishEvent(new CreatedMoneySendTransaction(UUID.randomUUID(), DateTime.now(), 100, "PLN"));
    }
}

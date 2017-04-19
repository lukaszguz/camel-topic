package pl.guz.domain.model.transaction;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.guz.domain.model.event.notify.EventNotifier;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final EventNotifier eventNotifier;

    public void createTransaction() {
        // ... creating transaction
        eventNotifier.publishEvent(new CreatedMoneySendTransaction(UUID.randomUUID(), new Date(), 100, "PLN"));
    }
}

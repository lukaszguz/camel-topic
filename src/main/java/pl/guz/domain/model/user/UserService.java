package pl.guz.domain.model.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.guz.domain.model.event.notify.EventNotifier;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private final EventNotifier eventNotifier;

    @PostConstruct
    void init() {
        createUser();
    }

    public void createUser() {
        // ... creating user
        eventNotifier.publishEvent(new CreateUser(new Date(), "Jan Kowalski"));
    }
}

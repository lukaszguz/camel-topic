package pl.guz.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import pl.guz.domain.model.event.notify.EventNotifier;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final EventNotifier eventNotifier;

    @SneakyThrows
    public void createUser(String name) {
        // ... creating user
        CreatedUser createdUser = new CreatedUser(UUID.randomUUID(),
                                                  DateTime.now(),
                                                  name);
        eventNotifier.publishEvent(createdUser);

//        DomainEventEntity one = cassandraOperations.selectOne("select * from events where id=75172253-4794-430d-b201-1d5effc56566", DomainEventEntity.class);
//        log.info("Catch one: {}", one);
//        objectMapper.readValue(one.getEvent(), CreatedUser.class);
    }
}

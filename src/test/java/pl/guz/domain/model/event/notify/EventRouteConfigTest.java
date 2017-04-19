package pl.guz.domain.model.event.notify;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.guz.domain.model.user.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventRouteConfigTest extends CamelTestSupport {

    @Autowired
    UserService userService;

    @Test
    public void testRoute() throws Exception {
        // given:
        RouteDefinition route = context.getRouteDefinition("event_notifier_route");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {

            }
        });

        // when:
        context.start();
        userService.createUser("Test");

    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new EventRouteConfig().eventRoute();
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }
}
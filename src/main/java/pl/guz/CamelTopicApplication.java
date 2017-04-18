package pl.guz;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class CamelTopicApplication {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new SpringApplication(CamelTopicApplication.class)
                .run(args);
        CamelSpringBootApplicationController applicationController =
                applicationContext.getBean(CamelSpringBootApplicationController.class);
        applicationController.run();
    }
}

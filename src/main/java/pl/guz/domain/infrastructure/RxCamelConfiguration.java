package pl.guz.domain.infrastructure;

import org.apache.camel.CamelContext;
import org.apache.camel.rx.ReactiveCamel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RxCamelConfiguration {

    @Bean
    ReactiveCamel reactiveCamel(CamelContext camelContext) {
        return new ReactiveCamel(camelContext);
    }
}

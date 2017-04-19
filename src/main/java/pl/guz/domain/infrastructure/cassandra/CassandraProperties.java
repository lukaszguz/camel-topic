package pl.guz.domain.infrastructure.cassandra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.data.cassandra")
public class CassandraProperties {

    @NotNull
    private String schemaAction;

    @NotNull
    private String contactPoints;

    @NotNull
    private String username;

    @NotNull
    private String password;
}

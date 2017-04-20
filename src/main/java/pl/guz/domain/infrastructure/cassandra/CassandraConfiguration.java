package pl.guz.domain.infrastructure.cassandra;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CustomConversions;
import pl.guz.serializer.DomainEventToStringConverter;

import java.util.List;

@Configuration
@AllArgsConstructor
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    private CassandraProperties properties;

    @Override
    protected String getKeyspaceName() {
        return "event_store";
    }

    @Override
    public CustomConversions customConversions() {
        List<Converter> converters = Lists.newArrayList(new DomainEventToStringConverter());
        return new CustomConversions(converters);
    }

    @Override
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setUsername(properties.getUsername());
        cluster.setPassword(properties.getPassword());
        cluster.setContactPoints(properties.getContactPoints());
        return cluster;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"pl.guz.domain.model.event"};
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(properties.getSchemaAction());
    }
}

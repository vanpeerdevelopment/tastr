package eu.tastr.infrastructure.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:test/tastr.infrastructure.default.test.properties", ignoreResourceNotFound = false),
    @PropertySource(value = "classpath:test/tastr.infrastructure.${tastr.environment}.test.properties", ignoreResourceNotFound = true)
})
public class InfrastructureTestPropertiesConfig {
}

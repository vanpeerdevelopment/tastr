package eu.tastr.service;

import eu.tastr.domain.spring.DomainConfig;
import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import eu.tastr.infrastructure.test.RollingBackIntegrationTest;
import eu.tastr.service.spring.ServiceConfig;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    ServiceConfig.class,
    DomainConfig.class,
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class ServiceIntegrationTest extends RollingBackIntegrationTest {
}

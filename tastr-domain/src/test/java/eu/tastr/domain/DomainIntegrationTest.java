package eu.tastr.domain;

import eu.tastr.domain.spring.DomainConfig;
import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import eu.tastr.infrastructure.test.RollingBackIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    DomainConfig.class,
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class DomainIntegrationTest extends RollingBackIntegrationTest {
}

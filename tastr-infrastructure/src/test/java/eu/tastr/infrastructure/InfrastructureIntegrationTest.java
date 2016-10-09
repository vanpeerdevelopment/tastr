package eu.tastr.infrastructure;

import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import eu.tastr.infrastructure.test.RollingBackIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class InfrastructureIntegrationTest extends RollingBackIntegrationTest {
}

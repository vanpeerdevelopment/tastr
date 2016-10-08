package be.vdab.vrijstellingenbeleid.infrastructure;

import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureTestPropertiesConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.test.RollingBackIntegrationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

@ContextConfiguration(classes = {
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class InfrastructureIntegrationTest extends RollingBackIntegrationTest {

    @Inject
    protected ApplicationContext context;
}

package be.vdab.vrijstellingenbeleid.domain;

import be.vdab.vrijstellingenbeleid.domain.spring.DomainConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureTestPropertiesConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.test.RollingBackIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    DomainConfig.class,
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class DomainIntegrationTest extends RollingBackIntegrationTest {
}

package be.vdab.vrijstellingenbeleid.api;

import be.vdab.vrijstellingenbeleid.api.spring.ApiConfig;
import be.vdab.vrijstellingenbeleid.domain.spring.DomainConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureTestPropertiesConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.test.RollingBackIntegrationTest;
import be.vdab.vrijstellingenbeleid.service.spring.ServiceConfig;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    ApiConfig.class,
    ServiceConfig.class,
    DomainConfig.class,
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class ApiIntegrationTest extends RollingBackIntegrationTest {
}

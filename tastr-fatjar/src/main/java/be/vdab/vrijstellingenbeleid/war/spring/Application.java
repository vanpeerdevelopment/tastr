package be.vdab.vrijstellingenbeleid.war.spring;

import be.vdab.vrijstellingenbeleid.api.spring.ApiConfig;
import be.vdab.vrijstellingenbeleid.domain.spring.DomainConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureConfig;
import be.vdab.vrijstellingenbeleid.service.spring.ServiceConfig;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({
    FatJarConfig.class,
    ApiConfig.class,
    ServiceConfig.class,
    DomainConfig.class,
    InfrastructureConfig.class
})
@EnableMetrics
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

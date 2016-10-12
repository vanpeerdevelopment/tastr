package eu.tastr.fatjar.spring;

import eu.tastr.api.spring.ApiConfig;
import eu.tastr.domain.spring.DomainConfig;
import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.service.spring.ServiceConfig;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
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
@EnableAutoConfiguration(exclude = {FlywayAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package be.vdab.vrijstellingenbeleid.api.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
    value = ApiConfig.API_BASE_PACKAGE,
    excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class)
)
@Import(InterceptorConfig.class)
public class ApiConfig {

    public static final String API_BASE_PACKAGE = "be.vdab.vrijstellingenbeleid.api";
}

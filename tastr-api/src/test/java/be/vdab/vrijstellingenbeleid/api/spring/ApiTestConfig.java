package be.vdab.vrijstellingenbeleid.api.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.FilterType.ANNOTATION;
import static org.springframework.context.annotation.FilterType.REGEX;

@ComponentScan(
    value = ApiTestConfig.API_BASE_PACKAGE,
    excludeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class),
        @ComponentScan.Filter(type = REGEX, pattern = "be.vdab.vrijstellingenbeleid.api.*Resource$"),
    }
)
public class ApiTestConfig {

    public static final String API_BASE_PACKAGE = "be.vdab.vrijstellingenbeleid.api";

}

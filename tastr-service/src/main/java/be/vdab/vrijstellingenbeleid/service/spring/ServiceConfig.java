package be.vdab.vrijstellingenbeleid.service.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
    value = ServiceConfig.SERVICE_BASE_PACKAGE,
    excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class))
@EnableTransactionManagement
public class ServiceConfig {

    public static final String SERVICE_BASE_PACKAGE = "be.vdab.vrijstellingenbeleid.service";

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}

package eu.tastr.domain.spring;

import eu.tastr.infrastructure.ddd.RepositoryFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
    value = DomainConfig.DOMAIN_BASE_PACKAGE,
    excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class))
@EnableJpaRepositories(basePackages = DomainConfig.DOMAIN_BASE_PACKAGE, repositoryFactoryBeanClass = RepositoryFactoryBean.class)
public class DomainConfig {

    public static final String DOMAIN_BASE_PACKAGE = "eu.tastr.domain";
}

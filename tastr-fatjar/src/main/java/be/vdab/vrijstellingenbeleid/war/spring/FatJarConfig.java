package be.vdab.vrijstellingenbeleid.war.spring;

import org.springframework.context.annotation.*;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
    value = FatJarConfig.WAR_BASE_PACKAGE,
    excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class))
@PropertySources({
    @PropertySource(value = "classpath:tastr.default.properties", ignoreResourceNotFound = false),
    @PropertySource(value = "file:${TASTR_PROPERTY_FILE_LOCATION}", ignoreResourceNotFound = true)
})
public class FatJarConfig {

    public static final String WAR_BASE_PACKAGE = "be.vdab.vrijstellingenbeleid.war";
}

package eu.tastr.service.architecture;

import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.regex.Pattern;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.fail;

public class TransactionalAndValidatedServicesTest extends UnitTest {

    private static Set<Class<?>> EXCLUDED_SERVICES = newHashSet();

    @Test
    public void servicesAreTransactionalAndValidated() {
        serviceClasses()
            .stream()
            .filter(serviceClass -> !EXCLUDED_SERVICES.contains(serviceClass))
            .forEach(serviceClass -> validateTransactionalAndValidated(serviceClass));
    }

    private void validateTransactionalAndValidated(Class<?> serviceClass) {
        System.out.printf("==== Checking service %s%n", serviceClass.getName());
        if (!(serviceClass.isAnnotationPresent(Transactional.class) && serviceClass.isAnnotationPresent(Validated.class)))
            fail(String.format(
                "'%s' is een service en moet dus @javax.transaction.Transactional en @org.springframework.validation.annotation.Validated zijn. " +
                    "Als deze klasse dit niet hoort te zijn kan deze toegevoegd worden aan de excluded services (te vinden in deze test-klasse).",
                serviceClass.getCanonicalName()));
    }

    private Set<Class<?>> serviceClasses() {
        return services()
            .stream()
            .map(service -> toServiceClass(service))
            .collect(toSet());
    }

    private Set<BeanDefinition> services() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Service")));
        return scanner.findCandidateComponents("eu.tastr.service");
    }

    private Class<?> toServiceClass(BeanDefinition service) {
        try {
            return Class.forName(service.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

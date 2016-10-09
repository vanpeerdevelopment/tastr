package eu.tastr.api.architecture;

import eu.tastr.infrastructure.test.UnitTest;
import com.codahale.metrics.annotation.Timed;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isPublic;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.fail;

public class PublicResourceMethodesZijnTimedTest extends UnitTest {

    @Test
    public void publiekeResourceMethodesZijnTimed() {
        publiekeResourceMethodes()
            .stream()
            .forEach(this::validatePubliekeResourceMethodeIsTimed);
    }

    private void validatePubliekeResourceMethodeIsTimed(Method publiekeResourceMethode) {
        System.out.printf("==== Checking publieke resource methode %s#%s%n", publiekeResourceMethode.getDeclaringClass().getName(), publiekeResourceMethode.getName());
        if (!publiekeResourceMethode.isAnnotationPresent(Timed.class)) {
            fail(String.format("'%s' is een publieke resource methode en moet dus timed zijn met @Timed", publiekeResourceMethode.getName()));
        }
    }

    private Set<Method> publiekeResourceMethodes() {
        return resources()
            .stream()
            .map(this::toClass)
            .flatMap(resourceClass -> Stream.of(resourceClass.getDeclaredMethods()))
            .filter(method -> isPublic(method.getModifiers()))
            .collect(toSet());
    }

    private Set<BeanDefinition> resources() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Resource")));
        return scanner.findCandidateComponents("eu.tastr.api");
    }

    private Class<?> toClass(BeanDefinition beanDefinition) {
        try {
            return Class.forName(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

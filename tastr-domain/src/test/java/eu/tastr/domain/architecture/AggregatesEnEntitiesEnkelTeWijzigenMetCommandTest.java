package eu.tastr.domain.architecture;

import eu.tastr.infrastructure.ddd.AggregateRoot;
import eu.tastr.infrastructure.ddd.BaseEntity;
import eu.tastr.infrastructure.test.UnitTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static eu.tastr.domain.TastrAssertions.fail;
import static java.lang.reflect.Modifier.isPrivate;
import static java.util.stream.Collectors.toList;

public class AggregatesEnEntitiesEnkelTeWijzigenMetCommandTest extends UnitTest {

    @Test
    public void aggregatesEnEntitiesEnkelTeWijzigenMetCommandTest() {
        List<Method> violatingMethods = aggregateAndBaseEntityMethods().stream()
            .filter(method -> !isPrivate(method.getModifiers()))
            .filter(method -> !method.getName().startsWith("heeft"))
            .filter(method -> !method.getName().startsWith("get"))
            .filter(method -> !method.getName().startsWith("is"))
            .collect(toList());
        if (violatingMethods.size() > 0) {
            violatingMethods.stream()
                .forEach(method -> System.out.printf("==== Aggregates en base entities mogen enkel gewijzigd worden via Aggragete::execute, verwijder %s#%s%n", method.getDeclaringClass().getName(), method.getName()));
            Assertions.fail("Aggregates en base entities mogen enkel gewijzigd worden via Aggregate::execute");
        }
    }

    private List<Method> aggregateAndBaseEntityMethods() {
        return aggregatesAndBaseEntities()
            .stream()
            .map(this::toClass)
            .flatMap(aggregateOrEntityClass -> Stream.of(aggregateOrEntityClass.getDeclaredMethods()))
            .collect(toList());
    }

    private Set<BeanDefinition> aggregatesAndBaseEntities() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(BaseEntity.class));
        scanner.addIncludeFilter(new AssignableTypeFilter(AggregateRoot.class));
        return scanner.findCandidateComponents("eu.tastr.domain");
    }

    private Class<?> toClass(BeanDefinition beanDefinition) {
        try {
            return Class.forName(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package be.vdab.vrijstellingenbeleid.domain.architecture;

import be.vdab.vrijstellingenbeleid.infrastructure.ddd.ValueObject;
import be.vdab.vrijstellingenbeleid.infrastructure.test.UnitTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.regex.Pattern;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.fail;

public class ValueObjectsZijnImmutableTest extends UnitTest {

    @Test
    public void alleValueObjectsZijnImmutable() throws Exception {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(ValueObject.class));
        scanner.addExcludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Test.*")));
        Set<BeanDefinition> valueObjects = scanner.findCandidateComponents("be.vdab.vrijstellingenbeleid");
        valueObjects.stream().forEach(beanDefinition -> {
            try {
                System.out.printf("==== Checking valueobject %s%n", beanDefinition.getBeanClassName());
                isImmutable(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void isImmutable(Class objClass) {
        classMoetFinalZijn(objClass);
        fieldsMoetenFinalZijn(objClass);
    }

    private void classMoetFinalZijn(Class objClass) {
        if (!Modifier.isFinal(objClass.getModifiers())) {
            Assertions.fail(String.format("'%s' is een ValueObject en moet dus final zijn", objClass.getCanonicalName()));
        }
    }

    private void fieldsMoetenFinalZijn(Class objClass) {
        Field[] objFields = objClass.getDeclaredFields();
        for (Field objField : objFields) {
            if (!Modifier.isFinal(objField.getModifiers())) {
                Assertions.fail(String.format("'%s' is een field van een ValueObject(%s) en moet dus final zijn", objField.getName(), objClass.getCanonicalName()));
            }
        }
    }
}

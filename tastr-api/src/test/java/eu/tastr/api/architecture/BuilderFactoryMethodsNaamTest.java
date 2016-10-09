package eu.tastr.api.architecture;

import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.fail;

public class BuilderFactoryMethodsNaamTest extends UnitTest {

    @Test
    public void testBuilderFactoryMethodsStartWithEen() {
        testBuilderFactoryMethods()
            .stream()
            .forEach(testBuilderFactoryMethod -> validateTestBuilderFactoryMethodsStartWithEen(testBuilderFactoryMethod));
    }

    private void validateTestBuilderFactoryMethodsStartWithEen(Method testBuilderFactoryMethod) {
        System.out.printf("==== Checking test builder factory method %s#%s%n", testBuilderFactoryMethod.getDeclaringClass().getName(), testBuilderFactoryMethod.getName());
        if(!testBuilderFactoryMethod.getName().startsWith("een")){
            fail(String.format("'%s' is een test builder factory method en moet dus beginnen met 'een'", testBuilderFactoryMethod.getName()));
        }
    }

    private Set<Method> testBuilderFactoryMethods() {
        return testBuilders()
            .stream()
            .map(testBuilderBeanDefinition -> toTestBuilderClass(testBuilderBeanDefinition))
            .filter(testBuilderClass -> isPublic(testBuilderClass.getModifiers()))
            .map(publicTestBuilderClass -> newHashSet(publicTestBuilderClass.getDeclaredMethods()))
            .flatMap(testBuilderMethods -> testBuilderMethods.stream())
            .filter(method -> isStatic(method.getModifiers()))
            .filter(method -> !isGeneratedMethod(method))
            .collect(toSet());
    }

    private boolean isGeneratedMethod(Method method) {
        return method.getName().startsWith("$");
    }

    private Set<BeanDefinition> testBuilders() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*TestBuilder")));
        return scanner.findCandidateComponents("eu.tastr");
    }

    @Test
    public void builderFactoryMethodsDonStartWithEen() {
        builderFactoryMethods()
            .stream()
            .forEach(testBuilderFactoryMethod -> validateBuilderFactoryMethodsDontStartWithEen(testBuilderFactoryMethod));
    }

    private Set<Method> builderFactoryMethods() {
        return builders()
            .stream()
            .map(testBuilderBeanDefinition -> toTestBuilderClass(testBuilderBeanDefinition))
            .filter(testBuilderClass -> isPublic(testBuilderClass.getModifiers()))
            .flatMap(publicTestBuilderClass -> newHashSet(publicTestBuilderClass.getDeclaredMethods()).stream())
            .filter(method -> isStatic(method.getModifiers()))
            .collect(toSet());
    }

    private Set<BeanDefinition> builders() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Builder")));
        scanner.addExcludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*TestBuilder")));
        return scanner.findCandidateComponents("eu.tastr");
    }

    private void validateBuilderFactoryMethodsDontStartWithEen(Method builderFactoryMethod) {
        System.out.printf("==== Checking builder factory method %s#%s%n", builderFactoryMethod.getDeclaringClass().getName(), builderFactoryMethod.getName());
        if(builderFactoryMethod.getName().startsWith("een")){
            fail(String.format("'%s' is een builder factory method en mag dus niet beginnen met 'een'", builderFactoryMethod.getName()));
        }
    }

    private Class<?> toTestBuilderClass(BeanDefinition testBuilder) {
        try {
            return Class.forName(testBuilder.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

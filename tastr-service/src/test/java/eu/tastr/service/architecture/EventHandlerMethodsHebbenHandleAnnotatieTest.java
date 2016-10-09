package eu.tastr.service.architecture;

import eu.tastr.infrastructure.messaging.EventHandler;
import eu.tastr.infrastructure.messaging.Handle;
import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Method;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.reflect.Modifier.isPublic;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.fail;

public class EventHandlerMethodsHebbenHandleAnnotatieTest extends UnitTest {

    @Test
    public void eventHandlersPubliekeMethodesHebbenHandleAnnotatie() {
        eventHandlerPubliekeMethodes().stream()
            .forEach(eventHandlerPubliekeMethode -> validateEventHandlerPubliekeMethodeHeeftHandleAnnotatie(eventHandlerPubliekeMethode));
    }

    private Set<Method> eventHandlerPubliekeMethodes() {
        return eventHandlers().stream()
            .map(eventHandlerBeanDefinition -> toEventHandlerClasses(eventHandlerBeanDefinition))
            .flatMap(eventHandlerClass -> newHashSet(eventHandlerClass.getDeclaredMethods()).stream())
            .filter(method -> isPublic(method.getModifiers()))
            .collect(toSet());
    }

    private Set<BeanDefinition> eventHandlers() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(EventHandler.class));
        return scanner.findCandidateComponents("eu.tastr.service");
    }

    private Class<?> toEventHandlerClasses(BeanDefinition eventHandlerBeanDefinition) {
        try {
            return Class.forName(eventHandlerBeanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateEventHandlerPubliekeMethodeHeeftHandleAnnotatie(Method eventHandlerPubliekeMethode) {
        System.out.printf("==== Checking event handler method %s#%s%n", eventHandlerPubliekeMethode.getDeclaringClass().getName(), eventHandlerPubliekeMethode.getName());
        if (!eventHandlerPubliekeMethode.isAnnotationPresent(Handle.class)) {
            fail(String.format("'%s' is een publieke event handler methode en moet dus de annotatie @Handle hebben", eventHandlerPubliekeMethode.getName()));
        }
    }
}

package eu.tastr.service.architecture;

import eu.tastr.infrastructure.messaging.EventHandler;
import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.Set;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.fail;

public class EventHandlersImplementerenEventHandlerTest extends UnitTest {

    @Test
    public void eventHandlersMoetenEventHandlerImplementeren() {
        eventHandlerClasses().stream()
            .forEach(this::validateEventHandlerImplementeertEventHandler);
    }

    private Set<Class<?>> eventHandlerClasses() {
        return eventHandlers().stream()
            .map(eventHandlerBeanDefinition -> toEventHandlerClasses(eventHandlerBeanDefinition))
            .collect(toSet());
    }

    private Set<BeanDefinition> eventHandlers() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile("^.*EventHandler$")));
        return scanner.findCandidateComponents("eu.tastr.service");
    }

    private Class<?> toEventHandlerClasses(BeanDefinition eventHandlerBeanDefinition) {
        try {
            return Class.forName(eventHandlerBeanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateEventHandlerImplementeertEventHandler(Class<?> eventHandler) {
        System.out.printf("==== Checking event handler %s%n", eventHandler.getName());
        if (!EventHandler.class.isAssignableFrom(eventHandler)) {
            fail(String.format("'%s' is een event handler en moet dus EventHandler implementeren", eventHandler.getName()));
        }
    }
}

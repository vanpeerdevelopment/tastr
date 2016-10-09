package eu.tastr.infrastructure.test.builder;

import eu.tastr.infrastructure.ddd.AggregateRoot;
import org.springframework.context.ApplicationContext;

public abstract class PersistableTestBuilder<T extends AggregateRoot<?>> extends TestBuilder<T> {

    @Override
    protected final T persistIfNecessary(T object, ApplicationContext context) {
        return persistObject(object, context);
    }

    protected abstract T persistObject(T object, ApplicationContext context);
}

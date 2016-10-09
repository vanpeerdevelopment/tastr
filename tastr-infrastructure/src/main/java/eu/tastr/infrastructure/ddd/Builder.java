package eu.tastr.infrastructure.ddd;

import static eu.tastr.infrastructure.validation.TastrValidator.validator;

public abstract class Builder<T> {

    protected abstract T buildInternal();

    public final T build() {
        T object = buildInternal();
        validator().validate(object);
        return object;
    }
}

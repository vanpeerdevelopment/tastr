package be.vdab.vrijstellingenbeleid.infrastructure.ddd;

import static be.vdab.vrijstellingenbeleid.infrastructure.validation.VrijstellingenbeleidValidator.validator;

public abstract class Builder<T> {

    protected abstract T buildInternal();

    public final T build() {
        T object = buildInternal();
        validator().validate(object);
        return object;
    }
}

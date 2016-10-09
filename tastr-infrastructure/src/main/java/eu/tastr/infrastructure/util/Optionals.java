package eu.tastr.infrastructure.util;

import java.util.Optional;
import java.util.stream.Stream;

public final class Optionals {

    private Optionals(){}

    public static <T> Stream<T> asStream(Optional<T> opt) {
        if (opt.isPresent())
            return Stream.of(opt.get());
        else
            return Stream.empty();
    }

}

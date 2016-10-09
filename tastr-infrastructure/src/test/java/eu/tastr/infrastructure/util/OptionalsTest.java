package eu.tastr.infrastructure.util;

import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalsTest extends UnitTest {

    @Test
    public void asStream_GivenEmptyOptional_ThenReturnStreamWithEmptyOptional(){
        Stream<Object> objectStream = Optionals.asStream(Optional.empty());

        assertThat(objectStream.findFirst().isPresent()).isFalse();
    }

    @Test
    public void asStream_GivenNonEmptyOptional_ThenReturnStreamWithNonEmptyOptional(){
        String optionalBody = "Hallo";

        Stream<Object> objectStream = Optionals.asStream(Optional.of(optionalBody));

        Optional<Object> firstObjectInStream = objectStream.findFirst();
        assertThat(firstObjectInStream.isPresent()).isTrue();
        assertThat(firstObjectInStream.get()).isEqualTo(optionalBody);
    }
}

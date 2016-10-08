package be.vdab.vrijstellingenbeleid.infrastructure.specification;

import be.vdab.vrijstellingenbeleid.infrastructure.test.UnitTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrSpecificationTest extends UnitTest {

    @Test
    public void or_GivenLeftTrueRightFalse_ThenShouldReturnTrue() {
        Assertions.assertThat(OrSpecification.or(SpecificationTestUtil.trueSpecification(), SpecificationTestUtil.falseSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void or_GivenLeftFalseRightTrue_ThenShouldReturnTrue() {
        Assertions.assertThat(OrSpecification.or(SpecificationTestUtil.falseSpecification(), SpecificationTestUtil.trueSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void or_GivenLeftTrueRightTrue_ThenShouldReturnTrue() {
        Assertions.assertThat(OrSpecification.or(SpecificationTestUtil.trueSpecification(), SpecificationTestUtil.trueSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void or_GivenLeftFalseRightFalse_ThenShouldReturnFalse() {
        Assertions.assertThat(OrSpecification.or(SpecificationTestUtil.falseSpecification(), SpecificationTestUtil.falseSpecification()).isSatisfiedBy("")).isFalse();
    }
}

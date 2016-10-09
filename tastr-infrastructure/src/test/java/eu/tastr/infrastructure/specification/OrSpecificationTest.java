package eu.tastr.infrastructure.specification;

import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrSpecificationTest extends UnitTest {

    @Test
    public void or_GivenLeftTrueRightFalse_ThenShouldReturnTrue() {
        assertThat(OrSpecification.or(SpecificationTestUtil.trueSpecification(), SpecificationTestUtil.falseSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void or_GivenLeftFalseRightTrue_ThenShouldReturnTrue() {
        assertThat(OrSpecification.or(SpecificationTestUtil.falseSpecification(), SpecificationTestUtil.trueSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void or_GivenLeftTrueRightTrue_ThenShouldReturnTrue() {
        assertThat(OrSpecification.or(SpecificationTestUtil.trueSpecification(), SpecificationTestUtil.trueSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void or_GivenLeftFalseRightFalse_ThenShouldReturnFalse() {
        assertThat(OrSpecification.or(SpecificationTestUtil.falseSpecification(), SpecificationTestUtil.falseSpecification()).isSatisfiedBy("")).isFalse();
    }
}

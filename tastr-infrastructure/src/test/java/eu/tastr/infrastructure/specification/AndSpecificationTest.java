package eu.tastr.infrastructure.specification;

import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;

import static eu.tastr.infrastructure.specification.AndSpecification.and;
import static org.assertj.core.api.Assertions.assertThat;

public class AndSpecificationTest extends UnitTest {

    @Test
    public void and_GivenLeftTrueAndRightTrue_ThenReturnTrue(){
        assertThat(and(SpecificationTestUtil.trueSpecification(), SpecificationTestUtil.trueSpecification()).isSatisfiedBy("")).isTrue();
    }

    @Test
    public void and_GivenLeftTrueAndRightFalse_ThenReturnFalse(){
        assertThat(and(SpecificationTestUtil.trueSpecification(), SpecificationTestUtil.falseSpecification()).isSatisfiedBy("")).isFalse();
    }

    @Test
    public void and_GivenLeftFalseAndRightTrue_ThenReturnFalse(){
        assertThat(and(SpecificationTestUtil.falseSpecification(), SpecificationTestUtil.trueSpecification()).isSatisfiedBy("")).isFalse();
    }

    @Test
    public void and_GivenLeftFalseAndRightfalse_ThenReturnFalse(){
        assertThat(and(SpecificationTestUtil.falseSpecification(), SpecificationTestUtil.falseSpecification()).isSatisfiedBy("")).isFalse();
    }
}

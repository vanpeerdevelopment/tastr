package be.vdab.vrijstellingenbeleid.infrastructure.specification;

import be.vdab.vrijstellingenbeleid.infrastructure.test.UnitTest;
import org.junit.Test;

import static be.vdab.vrijstellingenbeleid.infrastructure.specification.AndSpecification.and;
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

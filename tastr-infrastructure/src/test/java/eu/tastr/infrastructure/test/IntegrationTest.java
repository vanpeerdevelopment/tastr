package eu.tastr.infrastructure.test;

import eu.tastr.infrastructure.test.matcher.ConstraintViolationExceptionMatcher;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public abstract class IntegrationTest extends AbstractJUnit4SpringContextTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected void expectNoException() {
    }

    protected void expectException(Class<? extends Throwable> expectedException) {
        this.expectedException.expect(expectedException);
    }

    protected void expectExceptionWithMessage(Class<? extends Throwable> expectedException, String message) {
        this.expectedException.expect(expectedException);
        this.expectedException.expectMessage(message);
    }

    protected void expectIllegalArgumentExceptionWithMessage(String message) {
        expectExceptionWithMessage(IllegalArgumentException.class, message);
    }

    protected void expectIllegalStateExceptionWithMessage(String message) {
        expectExceptionWithMessage(IllegalStateException.class, message);
    }

    protected void expectConstraintViolationExceptionWithMessages(String... violations){
        expectedException.expect(ConstraintViolationExceptionMatcher.constraintViolationExceptionContainingViolationMessage(violations));
    }
}

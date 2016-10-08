package be.vdab.vrijstellingenbeleid.infrastructure.test;

import be.vdab.vrijstellingenbeleid.infrastructure.ddd.Id;
import be.vdab.vrijstellingenbeleid.infrastructure.exception.VrijstellingenbeleidEntityBestaatNietException;
import be.vdab.vrijstellingenbeleid.infrastructure.test.matcher.ConstraintViolationExceptionMatcher;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public abstract class RollingBackIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

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

    protected void expectExceptionWithCause(Class<? extends Throwable> expectedException, Matcher<? extends Throwable> cause) {
        this.expectedException.expect(expectedException);
        this.expectedException.expectCause(cause);
    }

    protected void expectIllegalArgumentExceptionWithMessage(String message) {
        expectExceptionWithMessage(IllegalArgumentException.class, message);
    }

    protected void expectIllegalStateExceptionWithMessage(String message) {
        expectExceptionWithMessage(IllegalStateException.class, message);
    }

    protected void expectConstraintViolationExceptionWithMessages(String... violations) {
        expectedException.expect(ConstraintViolationExceptionMatcher.constraintViolationExceptionContainingViolationMessage(violations));
    }

    protected void expectTastrEntityBestaatNietExceptionWithMessage(Id id) {
        expectExceptionWithMessage(
            VrijstellingenbeleidEntityBestaatNietException.class,
            String.format(
                "Het opgezochte element (met %s %s) bestaat niet (meer).",
                id.getClass().getSimpleName(),
                id.getValue())
        );
    }
}

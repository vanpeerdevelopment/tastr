package eu.tastr.e2e.common;

import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static com.google.common.base.Throwables.propagate;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;

public class RepeatableSelenideAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatableSelenideAction.class);

    private Runnable action;
    public static final int NUMBER_OF_ATTEMPTS = 7;
    public static final int BASE_TIMEOUT = 100;

    private RepeatableSelenideAction(Runnable action) {
        this.action = action;
    }

    public static RepeatableSelenideAction repeatAction(Runnable action) {
        return new RepeatableSelenideAction(action);
    }

    public <R> R until(Supplier<R> successCondition) {
        for (int attempt = 0; attempt < NUMBER_OF_ATTEMPTS; attempt++) {
            logRetry(attempt);
            runAction();
            waitForTimeout(calculateIncreasingTimeout(attempt));
            try { return successCondition.get(); } catch (UIAssertionError e) {}
        }
        throw new TimeoutException("Action was not completed successfully within " + NUMBER_OF_ATTEMPTS + " attemps.");
    }

    public void until(BooleanSupplier successCondition) {
        for (int attempt = 0; attempt < NUMBER_OF_ATTEMPTS; attempt++) {
            logRetry(attempt);
            runAction();
            waitForTimeout(calculateIncreasingTimeout(attempt));
            if (successCondition.getAsBoolean()) { return; }
        }
        throw new TimeoutException("Action was not completed successfully within " + NUMBER_OF_ATTEMPTS + " attemps.");
    }

    private void logRetry(int attempt) {
        if (attempt > 0) {
            LOGGER.info("Retrying attempt number " + attempt);
        }
    }

    private void runAction() {
        try {
            action.run();
        } catch (Throwable t) {
            LOGGER.warn("repeatable action uitvoeren gooide exception", t);
        }
    }

    private void waitForTimeout(long timeout) {
        try {
            sleep(timeout);
        } catch (InterruptedException e) {
            propagate(e);
        }
    }

    private long calculateIncreasingTimeout(long attempt) {
        return BASE_TIMEOUT * round(pow(2, attempt));
    }
}

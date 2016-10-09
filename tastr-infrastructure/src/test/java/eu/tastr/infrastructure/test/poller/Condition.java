package eu.tastr.infrastructure.test.poller;

public class Condition {

    private Runnable runnable;
    private Throwable exceptionToThrowAfterTimeout = new TimeOutException();

    public Condition(Runnable runnable) {
        this.runnable = runnable;
    }

    public Condition(final Assertion assertion) {
        this.runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    assertion.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public boolean validate() {
        try {
            runnable.run();
            return true;
        } catch (Throwable e) {
            exceptionToThrowAfterTimeout = e;
            return false;
        }
    }

    public Throwable throwableToThrowAfterTimeout() {
        return exceptionToThrowAfterTimeout;
    }

}

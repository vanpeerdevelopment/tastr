package eu.tastr.infrastructure.exception;

import java.util.UUID;

public class TastrException extends RuntimeException {

    private final String id;

    protected TastrException(String message, Throwable throwable, String id) {
        super(message, throwable);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static TastrException tastrException(Throwable throwable) {
        String id = UUID.randomUUID().toString();
        return new TastrException(throwable.getMessage(), throwable, id);
    }
}

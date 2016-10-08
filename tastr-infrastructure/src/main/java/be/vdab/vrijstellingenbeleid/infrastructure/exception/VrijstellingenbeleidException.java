package be.vdab.vrijstellingenbeleid.infrastructure.exception;

import java.util.UUID;

public class VrijstellingenbeleidException extends RuntimeException {

    private final String id;

    protected VrijstellingenbeleidException(String message, Throwable throwable, String id) {
        super(message, throwable);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static VrijstellingenbeleidException vrijstellingenbeleidException(Throwable throwable) {
        String id = UUID.randomUUID().toString();
        return new VrijstellingenbeleidException(throwable.getMessage(), throwable, id);
    }
}

package be.vdab.vrijstellingenbeleid.api.spring.exception;

import be.vdab.vrijstellingenbeleid.infrastructure.exception.TastrException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Error {

    private final String id;
    private final List<String> messages;

    private Error(String id, List<String> messages) {
        this.id = id;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public List<String> getMessages() {
        return messages;
    }

    public static Error error(TastrException exception) {
        return Error.error(exception, newArrayList(exception.getMessage()));
    }

    public static Error error(TastrException exception, List<String> messages) {
        return new Error(exception.getId(), newArrayList(messages));
    }
}

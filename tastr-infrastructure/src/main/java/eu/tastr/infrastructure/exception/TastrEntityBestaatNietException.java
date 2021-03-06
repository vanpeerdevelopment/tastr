package eu.tastr.infrastructure.exception;

import eu.tastr.infrastructure.ddd.Id;

import static java.util.UUID.randomUUID;

public class TastrEntityBestaatNietException extends TastrException {

    private final String entityId;

    private TastrEntityBestaatNietException(Id entityId) {
        super(String.format("Het opgezochte element (met %s %s) bestaat niet (meer).", entityId.getClass().getSimpleName(), entityId.getValue()), null, randomUUID().toString());
        this.entityId = entityId.getValue();
    }

    public String getEntityId() {
        return entityId;
    }

    public static TastrEntityBestaatNietException tastrEntityBestaatNietException(Id entityId) {
        return new TastrEntityBestaatNietException(entityId);
    }

}

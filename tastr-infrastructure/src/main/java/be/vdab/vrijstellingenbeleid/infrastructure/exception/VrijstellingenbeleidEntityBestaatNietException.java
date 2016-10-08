package be.vdab.vrijstellingenbeleid.infrastructure.exception;

import be.vdab.vrijstellingenbeleid.infrastructure.ddd.Id;

import static java.util.UUID.randomUUID;

public class VrijstellingenbeleidEntityBestaatNietException extends VrijstellingenbeleidException {

    private final String entityId;

    private VrijstellingenbeleidEntityBestaatNietException(Id entityId) {
        super(String.format("Het opgezochte element (met %s %s) bestaat niet (meer).", entityId.getClass().getSimpleName(), entityId.getValue()), null, randomUUID().toString());
        this.entityId = entityId.getValue();
    }

    public String getEntityId() {
        return entityId;
    }

    public static VrijstellingenbeleidEntityBestaatNietException vrijstellingenbeleidEntityBestaatNietException(Id entityId) {
        return new VrijstellingenbeleidEntityBestaatNietException(entityId);
    }

}

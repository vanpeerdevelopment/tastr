package be.vdab.vrijstellingenbeleid.infrastructure.messaging;

import be.vdab.vrijstellingenbeleid.infrastructure.ddd.Id;
import be.vdab.vrijstellingenbeleid.infrastructure.ddd.ValueObject;

import javax.validation.constraints.NotNull;

public abstract class Command<ID extends Id> extends ValueObject {

    @NotNull
    private final ID aggregateId;
    @NotNull
    private final Integer versie;

    protected Command(ID aggregateId, int versie) {
        this.aggregateId = aggregateId;
        this.versie = versie;
    }

    public ID getAggregateId() {
        return aggregateId;
    }

    public int getVersie() {
        return versie;
    }
}
